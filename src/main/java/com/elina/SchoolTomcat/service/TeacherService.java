package com.elina.SchoolTomcat.service;

import com.elina.SchoolTomcat.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    /*-----CREATE-----*/
    int saveTeacher(Teacher teacher);

    /*-----RETRIEVE-----*/
    List<Teacher> retrieveAllTeachers();
    Optional<Teacher> retrieveTeacherByID (int id);
    Optional<Teacher> retrieveTeacherByName (String firstName, String lastName);

    /*-----UPDATE-----*/
    void updateTeacher(Teacher teacher);

    /*-----DELETE-----*/
    void deleteTeacher(Teacher teacher);

}
