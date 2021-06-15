package com.elina.SchoolTomcat.service.impl;

import com.elina.SchoolTomcat.dao.impl.CourseDAOImpl;
import com.elina.SchoolTomcat.dao.impl.DepartmentDAOImpl;
import com.elina.SchoolTomcat.dao.impl.StudentDAOImpl;
import com.elina.SchoolTomcat.dao.impl.TeacherDAOImpl;
import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.model.Student;
import com.elina.SchoolTomcat.service.StudentService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentService{
    private EntityManager em;
    private CourseDAOImpl courseDAOImpl;
    private DepartmentDAOImpl departmentDAOImpl;
    private StudentDAOImpl studentDAOImpl;
    private TeacherDAOImpl teacherDAOImpl;
    public StudentServiceImpl(EntityManager em) {
        this.em=em;
        courseDAOImpl = new CourseDAOImpl(em);
        departmentDAOImpl = new DepartmentDAOImpl(em);
        studentDAOImpl = new StudentDAOImpl(em);
        teacherDAOImpl = new TeacherDAOImpl(em);
    }
    /*-----CREATE-----*/
    public void saveStudent(Student student)
    {
        begin();
        try{
            studentDAOImpl.saveElement(student);

            Student savedStudent = studentDAOImpl.retrieveElementByName(student.getFirstName(),student.getLastName()).get();

            //Update courses with student
            for(Course course:student.getCourses())
            {
                Course retrievedCourse = courseDAOImpl.retrieveElementByName(course.getName()).get();
                retrievedCourse.getStudents().add(savedStudent);
                courseDAOImpl.updateElement(course);
            }
            end();

        }catch(PersistenceException exc){
            em.getTransaction().rollback();
            throw exc;
        }

    }




    /*-----RETRIEVE-----*/
    public List<Student> retrieveAllStudents()
    {
        begin();
        return studentDAOImpl.retrieveAllElements();
    }
    public Optional<Student> retrieveStudentByID (int student_id)
    {
        begin();
        return studentDAOImpl.retrieveElementByID(student_id);
    }
    public Optional<Student> retrieveStudentByName (String firstName, String lastName)
    {
        begin();
        return studentDAOImpl.retrieveElementByName(firstName,lastName);
    }




    /*-----UPDATE-----*/
    public void updateStudent(Student student) //knows db's id, updates firstName/lastName/major
    {
        begin();
        try{
            studentDAOImpl.updateElement(student);
            end();
        }catch(PersistenceException exc){
            em.getTransaction().rollback();
            throw exc;
        }

    }




    /*-----DELETE-----*/
    public void deleteStudent(Student student)
    {
        begin();
        try{
            //No children deletion
            //Student deletion
            studentDAOImpl.deleteElement(student);
            end();
        }catch(PersistenceException exc){
            em.getTransaction().rollback();
            throw exc;
        }

    }
    /*-----OTHER-----*/
    public void changeMajor(Integer id, String major)
    {
        begin();
        try{
            studentDAOImpl.changeMajor(id, major);
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
