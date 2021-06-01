package com.elina.SchoolTomcat.dao.impl;

import com.elina.SchoolTomcat.dao.TeacherDAO;
import com.elina.SchoolTomcat.model.Teacher;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TeacherDAOImpl implements TeacherDAO {
    private EntityManager entityManager;
    public TeacherDAOImpl(EntityManager entityManager)
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
    public void saveElement(Teacher teacher)
    {
        begin();
        if (entityManager.contains(teacher)) {
            entityManager.merge(teacher);
        } else {
            entityManager.persist(teacher);
        }
        entityManager.getTransaction().commit();

    }
    /*============RETRIEVE============*/
    public List<Teacher> retrieveAllElements()
    {
        begin();
        return entityManager.createQuery("from Teacher").getResultList();
    }

    public Optional<Teacher> retrieveElementByID(int id)
    {
        begin();
        Teacher teacher = entityManager.find(Teacher.class, id);
        return teacher != null ? Optional.of(teacher) : Optional.empty();
    }

    /*============UPDATE============*/

    public void updateElement(Teacher teacher)
    {
        begin();
        Teacher teacherToUpdate = entityManager.find(Teacher.class, teacher.getId());
        entityManager.merge(teacherToUpdate);
        entityManager.getTransaction().commit();
    }


    /*============DELETE============*/
    public void deleteElement(Teacher teacher)
    {
        begin();
        Teacher teacherToDelete = entityManager.find(Teacher.class, teacher.getId());
        entityManager.remove(teacherToDelete);
        entityManager.getTransaction().commit();
    }

}
