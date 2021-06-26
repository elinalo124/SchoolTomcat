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

    /*-----------------------------CRUD---------------------------------------*/
    /*============CREATE============*/
    public void saveElement(Teacher teacher)
    {
        if (entityManager.contains(teacher)) {
            entityManager.merge(teacher);
        } else {
            entityManager.persist(teacher);
        }

    }
    /*============RETRIEVE============*/
    public List<Teacher> retrieveAllElements()
    {
        return entityManager.createQuery("SELECT t FROM Teacher t WHERE t.deletedFlag = :false").getResultList();
    }

    public Optional<Teacher> retrieveElementByID(int teacher_id)
    {
        Teacher teacher = entityManager.find(Teacher.class, teacher_id);
        return teacher != null ? Optional.of(teacher) : Optional.empty();
    }

    public Optional<Teacher> retrieveElementByName(String firstName, String lastName)
    {
        Teacher teacher = entityManager.createNamedQuery("Teacher.findByName", Teacher.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName",lastName)
                .getSingleResult();
        return teacher != null ? Optional.of(teacher) : Optional.empty();
    }
    /*============UPDATE============*/

    public void updateElement(Teacher teacher)
    {
        //Teacher teacherToUpdate = entityManager.find(Teacher.class, teacher.getId());
        //teacherToUpdate.setFirstName(teacher.getFirstName());
        //teacherToUpdate.setLastName(teacher.getLastName());
        //teacherToUpdate.setEducation(teacher.getEducation());
        entityManager.merge(teacher);
    }


    /*============DELETE============*/
    public void deleteElement(Teacher teacher)
    {
        Teacher teacherToDelete = entityManager.find(Teacher.class, teacher.getId());
        //entityManager.remove(teacherToDelete);
        teacherToDelete.setDeletedFlag(true);
        entityManager.merge(teacherToDelete);
    }
}
