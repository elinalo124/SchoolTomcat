package com.elina.SchoolTomcat.dao;

import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;

import java.util.Optional;

public interface DepartmentDAO extends CRUD<Department> {
    //Optional<Department> retrieveDepartmentByName(String name);
    //void addCourse(Integer id, Course course);
}