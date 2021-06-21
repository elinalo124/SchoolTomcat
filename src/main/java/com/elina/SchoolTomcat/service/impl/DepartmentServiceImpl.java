package com.elina.SchoolTomcat.service.impl;

import com.elina.SchoolTomcat.dao.CourseDAO;
import com.elina.SchoolTomcat.dao.DepartmentDAO;
import com.elina.SchoolTomcat.dao.StudentDAO;
import com.elina.SchoolTomcat.dao.TeacherDAO;
import com.elina.SchoolTomcat.dao.impl.CourseDAOImpl;
import com.elina.SchoolTomcat.dao.impl.DepartmentDAOImpl;
import com.elina.SchoolTomcat.dao.impl.StudentDAOImpl;
import com.elina.SchoolTomcat.dao.impl.TeacherDAOImpl;
import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.model.Student;
import com.elina.SchoolTomcat.service.DepartmentService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class DepartmentServiceImpl implements DepartmentService {

    private EntityManager em;
    private CourseDAO courseDAOImpl;
    private DepartmentDAO departmentDAOImpl;
    private StudentDAO studentDAOImpl;
    private TeacherDAO teacherDAOImpl;

    public DepartmentServiceImpl(EntityManager em) {
        this.em=em;
        courseDAOImpl = new CourseDAOImpl(em);
        departmentDAOImpl = new DepartmentDAOImpl(em);
        studentDAOImpl = new StudentDAOImpl(em);
        teacherDAOImpl = new TeacherDAOImpl(em);
    }

    /*-----CREATE-----*/
    public int saveDepartment(Department department)
    {
        begin();
        try{
            departmentDAOImpl.saveElement(department);

            /*
            Department savedDepartment = departmentDAOImpl.retrieveElementByName(department.getName()).get();
            //Update courses with department
            if(department.getCourses()!=null){
                for(Course course:department.getCourses())
                {
                    Course retrievedCourse = courseDAOImpl.retrieveElementByName(course.getName()).get();
                    retrievedCourse.setDepartment(savedDepartment);
                    courseDAOImpl.updateElement(retrievedCourse);
                }
            }

             */
            end();
            return 1;
        }catch(PersistenceException exc){
            em.getTransaction().rollback();
            throw exc;
        }

    }



    /*-----RETRIEVE-----*/
    public List<Department> retrieveAllDepartments()
    {
        begin();
        return departmentDAOImpl.retrieveAllElements();
    }

    public Optional<Department> retrieveDepartmentByID (int department_id)
    {
        begin();
        return departmentDAOImpl.retrieveElementByID(department_id);
    }
    public Optional<Department> retrieveDepartmentByName (String department_name)
    {
        begin();
        return departmentDAOImpl.retrieveElementByName(department_name);
    }



    /*-----UPDATE-----*/
    public void updateDepartment(Department department) //knows db's id, updates name
    {
        begin();
        try{
            departmentDAOImpl.updateElement(department);
            end();
        }catch(PersistenceException exc){
            em.getTransaction().rollback();
            throw exc;
        }
    }

    /*-----DELETE-----*/
    public void deleteDepartment(Department department)
    {
        begin();
        try{
            //Children deletion (The course still exists but it has no department)
            Department retrievedDepartment = departmentDAOImpl.retrieveElementByName(department.getName()).get();
            if(retrievedDepartment.getCourses()!=null){
                for(Course course:retrievedDepartment.getCourses())
                {
                    course.setDepartment(null);
                    courseDAOImpl.updateElement(course);
                }
            }
            //Department deletion
            departmentDAOImpl.deleteElement(retrievedDepartment);
            end();
        }catch(PersistenceException exc){
            em.getTransaction().rollback();
            throw exc;
        }
    }



    /*-----HELPER METHODS-----*/
    private void begin()
    {
        if(!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void end(){
        em.getTransaction().commit();
    }

}
