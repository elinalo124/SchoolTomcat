package com.elina.SchoolTomcat.service;

import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.model.Student;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    /*-----CREATE-----*/
    int saveCourse(Course course);

    /*-----RETRIEVE-----*/
    List<Course> retrieveAllCourses();
    Optional<Course> retrieveCourseByID(int id);
    Optional<Course> retrieveCourseByName(String name);

    /*-----UPDATE-----*/
    void updateCourse(Course course);

    /*-----DELETE-----*/
    void deleteCourse(Course course);
}
