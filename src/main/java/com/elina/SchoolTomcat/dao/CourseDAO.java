package com.elina.SchoolTomcat.dao;

import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Student;

import java.util.Optional;

public interface CourseDAO extends CRUD<Course>{

    Optional<Course> retrieveElementByName(String name);

}
