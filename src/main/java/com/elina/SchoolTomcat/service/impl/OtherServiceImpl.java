package com.elina.SchoolTomcat.service.impl;

import com.elina.SchoolTomcat.dao.impl.CourseDAOImpl;
import com.elina.SchoolTomcat.dao.impl.DepartmentDAOImpl;
import com.elina.SchoolTomcat.dao.impl.StudentDAOImpl;
import com.elina.SchoolTomcat.dao.impl.TeacherDAOImpl;
import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.model.Student;
import com.elina.SchoolTomcat.model.Teacher;
import com.elina.SchoolTomcat.util.JPASessionUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

public class OtherServiceImpl {
    private EntityManager em;
    private CourseDAOImpl courseDAOImpl;
    private DepartmentDAOImpl departmentDAOImpl;
    private StudentDAOImpl studentDAOImpl;
    private TeacherDAOImpl teacherDAOImpl;

    public OtherServiceImpl(EntityManager em) {
        this.em=em;
        courseDAOImpl = new CourseDAOImpl(em);
        departmentDAOImpl = new DepartmentDAOImpl(em);
        studentDAOImpl = new StudentDAOImpl(em);
        teacherDAOImpl = new TeacherDAOImpl(em);
    }




    /*----------------------------------------ENTITY RELATIONSHIP----------------------------------------------------*/
    public void addCourseToDepartment(int course_id, int department_id) {
        begin();
        try {
            Course retrievedCourse = courseDAOImpl.retrieveElementByID(course_id).get();
            Department retrievedDepartment = departmentDAOImpl.retrieveElementByID(department_id).get();
            retrievedCourse.setDepartment(retrievedDepartment);
            retrievedDepartment.getCourses().add(retrievedCourse);
            courseDAOImpl.updateElement(retrievedCourse);
            departmentDAOImpl.updateElement(retrievedDepartment);
            end();
        } catch (PersistenceException exc) {
            em.getTransaction().rollback();
            throw exc;
        }
    }

    public void addStudentToCourse(int student_id, int course_id){
        begin();
        try {
            Student retrievedStudent = studentDAOImpl.retrieveElementByID(student_id).get();
            Course retrievedCourse = courseDAOImpl.retrieveElementByID(course_id).get();
            retrievedStudent.getCourses().add(retrievedCourse);
            retrievedStudent.getCourses().add(retrievedCourse);
            courseDAOImpl.updateElement(retrievedCourse);
            studentDAOImpl.updateElement(retrievedStudent);
            end();
        } catch (PersistenceException exc) {
            em.getTransaction().rollback();
            throw exc;
        }
    }

    public void addTeacherToCourse(int teacher_id, int course_id){
        begin();
        try {
            Teacher retrievedTeacher = teacherDAOImpl.retrieveElementByID(teacher_id).get();
            Course retrievedCourse = courseDAOImpl.retrieveElementByID(course_id).get();
            retrievedTeacher.setCourse(retrievedCourse);
            retrievedCourse.setTeacher(retrievedTeacher);
            courseDAOImpl.updateElement(retrievedCourse);
            teacherDAOImpl.updateElement(retrievedTeacher);
            end();
        } catch (PersistenceException exc) {
            em.getTransaction().rollback();
            throw exc;
        }
    }

    public void removeCourseFromDepartment(int course_id, int department_id) {
        begin();
        try {
            Course retrievedCourse = courseDAOImpl.retrieveElementByID(course_id).get();
            Department retrievedDepartment = departmentDAOImpl.retrieveElementByID(department_id).get();
            retrievedCourse.setDepartment(null);
            retrievedDepartment.getCourses().remove(retrievedCourse);
            courseDAOImpl.updateElement(retrievedCourse);
            departmentDAOImpl.updateElement(retrievedDepartment);
            end();
        } catch (PersistenceException exc) {
            em.getTransaction().rollback();
            throw exc;
        }
    }

    public void removeStudentFromCourse(int student_id, int course_id){
        begin();
        try {
            Student retrievedStudent = studentDAOImpl.retrieveElementByID(student_id).get();
            Course retrievedCourse = courseDAOImpl.retrieveElementByID(course_id).get();
            retrievedStudent.getCourses().remove(retrievedCourse);
            retrievedStudent.getCourses().remove(retrievedCourse);
            courseDAOImpl.updateElement(retrievedCourse);
            studentDAOImpl.updateElement(retrievedStudent);
            end();
        } catch (PersistenceException exc) {
            em.getTransaction().rollback();
            throw exc;
        }
    }

    public void removeTeacherFromCourse(int teacher_id, int course_id){
        begin();
        try {
            Teacher retrievedTeacher = teacherDAOImpl.retrieveElementByID(teacher_id).get();
            Course retrievedCourse = courseDAOImpl.retrieveElementByID(course_id).get();
            retrievedTeacher.setCourse(null);
            retrievedCourse.setTeacher(null);
            courseDAOImpl.updateElement(retrievedCourse);
            teacherDAOImpl.updateElement(retrievedTeacher);
            end();
        } catch (PersistenceException exc) {
            em.getTransaction().rollback();
            throw exc;
        }
    }

    /*----------------------------------------COMPLETE DELETION----------------------------------------------------*/











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
