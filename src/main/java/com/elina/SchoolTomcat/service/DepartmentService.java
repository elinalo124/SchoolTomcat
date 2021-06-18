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
    Optional<Department> retrieveDepartmentByName (String name);

    /*-----UPDATE-----*/
    void updateDepartment(Department department);

    /*-----DELETE-----*/
    void deleteDepartment(Department department);
}
