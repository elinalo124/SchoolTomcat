package com.elina.SchoolTomcat.dao;

import com.elina.SchoolTomcat.model.Teacher;

import java.util.Optional;

public interface TeacherDAO extends CRUD<Teacher>{
    Optional<Teacher> retrieveElementByName(String firstName, String lastName);
}
