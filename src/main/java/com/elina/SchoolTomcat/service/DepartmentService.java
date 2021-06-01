package com.elina.SchoolTomcat.service;

import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    /*-----CREATE-----*/
    int saveDepartment(Department department);
    /*-----RETRIEVE-----*/
    List<Department> retrieveAllDepartments();
    Optional<Department> retrieveDepartmentByID (int id);
    /*-----UPDATE-----*/
    void updateDepartment(Department department);
    /*-----DELETE-----*/
    void deleteDepartment(Department department);
    /*-----OTHER-----*/
    Optional<Department> retrieveDepartmentByName(String name);
    void addCourse(Integer id, Course course);

}
