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
import com.elina.SchoolTomcat.model.Teacher;
import com.elina.SchoolTomcat.service.OtherService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

public class OtherServiceImpl implements OtherService {

    private EntityManager em;
    private CourseDAO courseDAOImpl;
    private DepartmentDAO departmentDAOImpl;
    private StudentDAO studentDAOImpl;
    private TeacherDAO teacherDAOImpl;

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


    /*--------------------------------------BULK ADDITION AND REMOVAL-------------------------------------------------*/

    public int studentBulkAdd(List<Student> students){
        begin();
        try{
            for(Student student:students){
                studentDAOImpl.saveElement(student);
                Student savedStudent = studentDAOImpl.retrieveElementByName(student.getFirstName(),student.getLastName()).get();
                //Update courses with student
                for(Course course:student.getCourses())
                {
                    Course retrievedCourse = courseDAOImpl.retrieveElementByName(course.getName()).get();
                    retrievedCourse.getStudents().add(savedStudent);
                    courseDAOImpl.updateElement(course);
                }
            }
            end();
            return 1;
        }catch(PersistenceException exc){
            em.getTransaction().rollback();
            throw exc;
        }
    }

    public int courseBulkAdd(List<Course> courses){
        begin();
        try{
            for (Course course:courses){
                //Save course
                courseDAOImpl.saveElement(course);
                Course savedCourse = courseDAOImpl.retrieveElementByName(course.getName()).get();
                //Update department with new course
                if(course.getDepartment()!=null){
                    Department retrievedDepartment = departmentDAOImpl.retrieveElementByName(course.getDepartment().getName()).get();
                    retrievedDepartment.getCourses().add(savedCourse);
                    departmentDAOImpl.updateElement(retrievedDepartment);
                }
                //Update teacher with new course
                if(course.getTeacher()!=null){
                    Teacher retrievedTeacher = teacherDAOImpl.retrieveElementByName(course.getTeacher().getFirstName(), course.getTeacher().getLastName()).get();
                    retrievedTeacher.setCourse(savedCourse);
                    teacherDAOImpl.updateElement(retrievedTeacher);
                }
                //Update student with new course
                if(course.getTeacher()!=null){
                    for(Student student:course.getStudents())
                    {
                        Student retrievedStudent = studentDAOImpl.retrieveElementByName(student.getFirstName(),student.getLastName()).get();
                        retrievedStudent.getCourses().add(savedCourse);
                        studentDAOImpl.updateElement(retrievedStudent);
                    }
                }
            }
            end();
            return 1;
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
