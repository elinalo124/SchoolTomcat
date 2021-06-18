package com.elina.SchoolTomcat.dao;

import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.model.Student;

import java.util.Optional;

public interface StudentDAO extends CRUD<Student>{
    Optional<Student> retrieveElementByName(String firstName, String lastName);
}
