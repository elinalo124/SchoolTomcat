package com.elina.SchoolTomcat.service.impl;

import com.elina.SchoolTomcat.dao.impl.CourseDAOImpl;
import com.elina.SchoolTomcat.dao.impl.DepartmentDAOImpl;
import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.service.DepartmentService;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class DepartmentServiceImpl implements DepartmentService {

    private EntityManager em;
    private DepartmentDAOImpl departmentDAOImpl;
    private CourseDAOImpl courseDAOImpl;

    public DepartmentServiceImpl(EntityManager em) {
        this.em=em;
        departmentDAOImpl = new DepartmentDAOImpl(em);
        courseDAOImpl = new CourseDAOImpl(em);
    }

    /*-----CREATE-----*/
    public int saveDepartment(Department department)
    {
        departmentDAOImpl.saveElement(department);
        for(Course course:department.getCourses())
        {
            course.setDepartment(department);
            courseDAOImpl.updateElement(course);
        }
        return 1;
    }

    /*-----RETRIEVE-----*/
    public List<Department> retrieveAllDepartments()
    {
        return departmentDAOImpl.retrieveAllElements();
    }

    public Optional<Department> retrieveDepartmentByID (int id)
    {
        return departmentDAOImpl.retrieveElementByID(id);
    }
    public Optional<Department> retrieveDepartmentByName (String name)
    {
        return departmentDAOImpl.retrieveDepartmentByName(name);
    }
    /*-----UPDATE-----*/
    public void updateDepartment(Department department)
    {
        departmentDAOImpl.updateElement(department);
    }
    /*-----DELETE-----*/
    public void deleteDepartment(Department department)
    {
        departmentDAOImpl.deleteElement(department);
    }
    /*-----OTHER-----*/
    public void addCourse(Integer id, Course course)
    {
        departmentDAOImpl.addCourse(id,course);
    }

}
