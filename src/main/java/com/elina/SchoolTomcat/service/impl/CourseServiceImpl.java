package com.elina.SchoolTomcat.service.impl;

import com.elina.SchoolTomcat.dao.impl.CourseDAOImpl;
import com.elina.SchoolTomcat.dao.impl.DepartmentDAOImpl;
import com.elina.SchoolTomcat.dao.impl.StudentDAOImpl;
import com.elina.SchoolTomcat.dao.impl.TeacherDAOImpl;
import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.model.Student;
import com.elina.SchoolTomcat.model.Teacher;
import com.elina.SchoolTomcat.service.CourseService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {

    private EntityManager em;
    private CourseDAOImpl courseDAOImpl;
    private DepartmentDAOImpl departmentDAOImpl;
    private StudentDAOImpl studentDAOImpl;
    private TeacherDAOImpl teacherDAOImpl;

    public CourseServiceImpl(EntityManager em) {
        this.em=em;
        courseDAOImpl = new CourseDAOImpl(em);
        departmentDAOImpl = new DepartmentDAOImpl(em);
        studentDAOImpl = new StudentDAOImpl(em);
        teacherDAOImpl = new TeacherDAOImpl(em);
    }

    /*-----CREATE-----*/
    public void saveCourse(Course course)
    {
        begin();
        try{
            //Save course
            courseDAOImpl.saveElement(course);

            Course savedCourse = courseDAOImpl.retrieveElementByName(course.getName()).get();

            //Update department with new course
            if(savedCourse.getDepartment()!=null){
                Department retrievedDepartment = departmentDAOImpl.retrieveElementByName(savedCourse.getDepartment().getName()).get();
                retrievedDepartment.getCourses().add(savedCourse);
                departmentDAOImpl.updateElement(retrievedDepartment);
            }

            //Update teacher with new course
            if(savedCourse.getTeacher()!=null){
                Teacher retrievedTeacher = teacherDAOImpl.retrieveElementByName(savedCourse.getTeacher().getFirstName(), savedCourse.getTeacher().getLastName()).get();
                retrievedTeacher.setCourse(savedCourse);
                teacherDAOImpl.updateElement(retrievedTeacher);
            }

            //Update student with new course
            for(Student student:savedCourse.getStudents())
            {
                Student retrievedStudent = studentDAOImpl.retrieveElementByName(student.getFirstName(),student.getLastName()).get();
                retrievedStudent.getCourses().add(savedCourse);
                studentDAOImpl.updateElement(retrievedStudent);
            }

            end();
        }catch(PersistenceException exc){
            em.getTransaction().rollback();
            throw exc;
        }
    }




    /*-----RETRIEVE-----*/
    public List<Course> retrieveAllCourses()
    {
        begin();
        return courseDAOImpl.retrieveAllElements();
    }
    public Optional<Course> retrieveCourseByID (int course_id)
    {
        begin();
        return courseDAOImpl.retrieveElementByID(course_id);
    }
    public Optional<Course> retrieveCourseByName(String course_name)
    {
        begin();
        return courseDAOImpl.retrieveElementByName(course_name);
    }




    /*-----UPDATE-----*/
    public void updateCourse(Course course) //knows db's id, updates name/description
    {
        begin();
        try{
            courseDAOImpl.updateElement(course);
            end();
        }catch(PersistenceException exc){
            em.getTransaction().rollback();
            throw exc;
        }
    }




    /*-----DELETE-----*/
    public void deleteCourse(Course course)
    {
        begin();
        try{
            //Children deletion
            Course retrievedCourse = courseDAOImpl.retrieveElementByName(course.getName()).get();
            for(Student student:retrievedCourse.getStudents())
            {
                studentDAOImpl.deleteElement(student);
            }
            teacherDAOImpl.deleteElement(retrievedCourse.getTeacher());

            //Course deletion
            courseDAOImpl.deleteElement(course);
            end();
        }catch(PersistenceException exc){
            em.getTransaction().rollback();
            throw exc;
        }
    }
    /*-----OTHER-----*/
    /*
    public void addStudent(int id, Student student)
    {
        begin();
        try{
            courseDAOImpl.addStudent(id,student);
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
