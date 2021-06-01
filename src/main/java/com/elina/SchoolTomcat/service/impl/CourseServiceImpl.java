package com.elina.SchoolTomcat.service.impl;

import com.elina.SchoolTomcat.dao.impl.CourseDAOImpl;
import com.elina.SchoolTomcat.dao.impl.StudentDAOImpl;
import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Student;
import com.elina.SchoolTomcat.service.CourseService;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class CourseServiceImpl implements CourseService {

    private EntityManager em;
    private CourseDAOImpl courseDAOImpl;
    private StudentDAOImpl studentDAOImpl;

    public CourseServiceImpl(EntityManager em) {
        this.em=em;
        courseDAOImpl = new CourseDAOImpl(em);
        studentDAOImpl = new StudentDAOImpl(em);
    }

    /*-----CREATE-----*/
    public void saveCourse(Course course)
    {
        courseDAOImpl.saveElement(course);
        for(Student student:course.getStudents())
        {
            student.getCourses().add(course);
            studentDAOImpl.updateElement(student);
        }
    }

    /*-----RETRIEVE-----*/
    public List<Course> retrieveAllCourses()
    {
        return courseDAOImpl.retrieveAllElements();
    }
    public Optional<Course> retrieveCourseByID (int id)
    {
        return courseDAOImpl.retrieveElementByID(id);
    }
    /*-----UPDATE-----*/
    public void updateCourse(Course course)
    {
        courseDAOImpl.updateElement(course);
    }
    /*-----DELETE-----*/
    public void deleteCourse(Course department)
    {
        courseDAOImpl.deleteElement(department);
    }
    /*-----OTHER-----*/
    public Optional<Course> retrieveCourseByName(String name)
    {
        return courseDAOImpl.retrieveCourseByName(name);
    }
    public void addStudent(int id, Student student)
    {
        courseDAOImpl.addStudent(id,student);
    }

}
