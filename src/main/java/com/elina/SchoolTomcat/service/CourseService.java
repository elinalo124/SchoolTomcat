package com.elina.SchoolTomcat.service;

import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.model.Student;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    /*-----CREATE-----*/
    void saveCourse(Course course);
    /*-----RETRIEVE-----*/
    List<Course> retrieveAllCourses();
    Optional<Course> retrieveCourseByID (int id);
    /*-----UPDATE-----*/
    void updateCourse(Course course);
    /*-----DELETE-----*/
    void deleteCourse(Course course);
    /*-----OTHER-----*/
    Optional<Course> retrieveCourseByName(String name);
    void addStudent(int id, Student student);
}
