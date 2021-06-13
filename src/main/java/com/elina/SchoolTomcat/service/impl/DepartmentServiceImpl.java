package com.elina.SchoolTomcat.service.impl;

import com.elina.SchoolTomcat.dao.impl.CourseDAOImpl;
import com.elina.SchoolTomcat.dao.impl.DepartmentDAOImpl;
import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.service.DepartmentService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class DepartmentServiceImpl implements DepartmentService {

    private EntityManager em;
    private DepartmentDAOImpl departmentDAOImpl;
    private CourseDAOImpl courseDAOImpl;

    public DepartmentServiceImpl(EntityManager em) {
        this.em=em;
        departmentDAOImpl = new DepartmentDAOImpl(em);
        courseDAOImpl = new CourseDAOImpl(em);
    }

    /*-----CREATE-----*/
    public int saveDepartment(Department department)
    {
        begin();
        try{
            departmentDAOImpl.saveElement(department);

            int department_id = departmentDAOImpl.retrieveDepartmentByName(department.getName()).get().getId();
            for(Course course:department.getCourses())
            {
                courseDAOImpl.setDepartment(department_id, course);
            }
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
    public Optional<Department> retrieveDepartmentByName (String name)
    {
        begin();
        return departmentDAOImpl.retrieveDepartmentByName(name);
    }
    /*-----UPDATE-----*/
    public int updateDepartment(Department department)
    {
        begin();
        try{
            departmentDAOImpl.updateElement(department);
            int department_id = departmentDAOImpl.retrieveDepartmentByName(department.getName()).get().getId();
            for(Course course:department.getCourses())
            {
                courseDAOImpl.setDepartment(department_id, course);
            }
            end();
            return 1;
        }catch(PersistenceException exc){
            em.getTransaction().rollback();
            throw exc;
        }

    }
    public int updateDepartmentByID(int id, String name) {
        begin();
        try{
            departmentDAOImpl.updateElementByID(id,name);
            end();
            return 1;
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
            departmentDAOImpl.deleteElement(department);
            end();
        }catch(PersistenceException exc){
            em.getTransaction().rollback();
            throw exc;
        }
    }
    public void deleteDepartmentByID(int id)
    {
        begin();
        try{
            Department department = departmentDAOImpl.retrieveElementByID(id).get();
            departmentDAOImpl.deleteElement(department);
            end();
        }catch(PersistenceException exc){
            em.getTransaction().rollback();
            throw exc;
        }
    }
    /*-----OTHER-----*/
    public void addCourse(Integer department_id, Course course)
    {
        begin();
        try{
            departmentDAOImpl.addCourse(department_id,course);
            courseDAOImpl.setDepartment(department_id,course);
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
