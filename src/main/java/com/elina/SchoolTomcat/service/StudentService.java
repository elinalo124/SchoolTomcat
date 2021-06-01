package com.elina.SchoolTomcat.service;

import com.elina.SchoolTomcat.model.Student;
import com.elina.SchoolTomcat.model.Department;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    /*-----CREATE-----*/
    void saveStudent(Student student);
    /*-----RETRIEVE-----*/
    List<Student> retrieveAllStudents();
    Optional<Student> retrieveStudentByID (int id);
    /*-----UPDATE-----*/
    void updateStudent(Student student);
    /*-----DELETE-----*/
    void deleteStudent(Student student);
    /*-----OTHER-----*/
    void changeMajor(Integer id, String major);
}
