package com.elina.SchoolTomcat.dao.impl;

import com.elina.SchoolTomcat.dao.StudentDAO;
import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.model.Student;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class StudentDAOImpl implements StudentDAO{

    private EntityManager entityManager;
    public StudentDAOImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    private void begin()
    {
        if(!entityManager.getTransaction().isActive())
            entityManager.getTransaction().begin();
    }
    /*-----------------------------CRUD---------------------------------------*/
    /*============CREATE============*/
    public void saveElement(Student student)
    {
        begin();
        if (entityManager.contains(student)) {
            entityManager.merge(student);
        } else {
            entityManager.persist(student);
        }
        entityManager.getTransaction().commit();

    }
    /*============RETRIEVE============*/
    public List<Student> retrieveAllElements()
    {
        begin();
        return entityManager.createQuery("from Student").getResultList();
    }

    public Optional<Student> retrieveElementByID(int id)
    {
        begin();
        Student student = entityManager.find(Student.class, id);
        return student != null ? Optional.of(student) : Optional.empty();
    }

    /*============UPDATE============*/

    public void updateElement(Student student)
    {
        begin();
        Student studentToUpdate = entityManager.find(Student.class, student.getId());
        entityManager.merge(studentToUpdate);
        entityManager.getTransaction().commit();
    }

    public void changeMajor(Integer id, String major)
    {
        begin();
        Student studentToUpdate = entityManager.find(Student.class, id);
        studentToUpdate.setMajor(major);
        entityManager.getTransaction().commit();
    }

    /*============DELETE============*/
    public void deleteElement(Student student)
    {
        begin();
        Student studentToDelete = entityManager.find(Student.class, student.getId());
        System.out.print(studentToDelete);
        entityManager.remove(studentToDelete);
        entityManager.getTransaction().commit();
    }

}
