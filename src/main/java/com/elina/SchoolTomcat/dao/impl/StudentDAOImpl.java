package com.elina.SchoolTomcat.dao.impl;

import com.elina.SchoolTomcat.dao.StudentDAO;
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

    /*-----------------------------CRUD---------------------------------------*/
    /*============CREATE============*/
    public void saveElement(Student student)
    {
        if (entityManager.contains(student)) {
            entityManager.merge(student);
        } else {
            entityManager.persist(student);
        }

    }
    /*============RETRIEVE============*/
    public List<Student> retrieveAllElements()
    {
        return entityManager.createQuery("SELECT s FROM Student s WHERE s.deletedFlag = :false").getResultList();
    }

    public Optional<Student> retrieveElementByID(int student_id)
    {
        Student student = entityManager.find(Student.class, student_id);
        return student != null ? Optional.of(student) : Optional.empty();
    }
    public Optional<Student> retrieveElementByName(String firstName, String lastName)
    {
        Student student = entityManager.createNamedQuery("Student.findByName", Student.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName",lastName)
                .getSingleResult();
        return student != null ? Optional.of(student) : Optional.empty();
    }

    /*============UPDATE============*/

    public void updateElement(Student student)
    {
        //Student studentToUpdate = entityManager.find(Student.class, student.getId());
        //studentToUpdate.setFirstName(student.getFirstName());
        //studentToUpdate.setLastName(student.getLastName());
        //studentToUpdate.setMajor(student.getMajor());
        entityManager.merge(student);
    }

    /*============DELETE============*/
    public void deleteElement(Student student)
    {
        Student studentToDelete = entityManager.find(Student.class, student.getId());
        //entityManager.remove(studentToDelete);
        studentToDelete.setDeletedFlag(true);
        entityManager.merge(studentToDelete);
    }

}
