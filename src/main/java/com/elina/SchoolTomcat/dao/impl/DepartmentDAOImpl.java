package com.elina.SchoolTomcat.dao.impl;

import com.elina.SchoolTomcat.dao.DepartmentDAO;
import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class DepartmentDAOImpl implements DepartmentDAO {

    private EntityManager entityManager;
    public DepartmentDAOImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }



    /*-----------------------------CRUD---------------------------------------*/
    /*============CREATE============*/
    public void saveElement(Department department)
    {
        if (entityManager.contains(department)) {
            entityManager.merge(department);
        } else {
            entityManager.persist(department);
        }

    }

    /*============RETRIEVE============*/
    public List<Department> retrieveAllElements()
    {
        return entityManager.createQuery("from Department").getResultList();
    }

    public Optional<Department> retrieveElementByID(int id)
    {
        Department department = entityManager.find(Department.class, id);
        return department != null? Optional.of(department): Optional.empty();
    }


    public Optional<Department> retrieveDepartmentByName(String name)
    {
        Department department = entityManager.createNamedQuery("Department.findByName", Department.class)
                .setParameter("name", name)
                .getSingleResult();
        return department != null ? Optional.of(department) : Optional.empty();
    }
    /*============UPDATE============*/
    public void updateElement(Department department)
    {
        Department departmentToUpdate = entityManager.find(Department.class, department.getId());
        departmentToUpdate.setName(department.getName());
        departmentToUpdate.setCourses(department.getCourses());
        entityManager.merge(departmentToUpdate);
    }
    public void updateElementByID(int id, String name) {
        Department departmentToUpdate = entityManager.find(Department.class, id);
        departmentToUpdate.setName(name);
        entityManager.merge(departmentToUpdate);
    }
    public void addCourse(Integer id, Course course)
    {
        Department departmentToUpdate = entityManager.find(Department.class, id);
        departmentToUpdate.getCourses().add(course);
        entityManager.merge(departmentToUpdate);
    }

    /*============DELETE============*/
    public void deleteElement(Department department)
    {
        Department departmentToDelete = entityManager.find(Department.class, department.getId());
        entityManager.remove(departmentToDelete);
    }


}

