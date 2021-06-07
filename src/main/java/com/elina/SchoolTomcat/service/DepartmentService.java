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
    Optional<Department> retrieveDepartmentByName(String name);
    /*-----UPDATE-----*/
    int updateDepartment(Department department);
    int updateDepartmentByID(int id, String name);
    /*-----DELETE-----*/
    void deleteDepartment(Department department);
    void deleteDepartmentByID(int id);
    /*-----OTHER-----*/
    void addCourse(Integer id, Course course);

}
