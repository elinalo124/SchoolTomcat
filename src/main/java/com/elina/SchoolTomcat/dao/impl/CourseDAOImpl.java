package com.elina.SchoolTomcat.dao.impl;

import com.elina.SchoolTomcat.dao.CourseDAO;
import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.model.Student;
import com.elina.SchoolTomcat.model.Teacher;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class CourseDAOImpl implements CourseDAO{

    private EntityManager entityManager;
    public CourseDAOImpl(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }



    /*-----------------------------CRUD---------------------------------------*/
    /*============CREATE============*/
    public void saveElement(Course course)
    {
        if (entityManager.contains(course)) {
            entityManager.merge(course);
        } else {
            entityManager.persist(course);
        }
    }
    /*============RETRIEVE============*/
    public List<Course> retrieveAllElements()
    {
        return entityManager.createQuery("from Course").getResultList();
    }
    public Optional<Course> retrieveElementByID(int course_id)
    {
        Course course = entityManager.find(Course.class, course_id);
        return course != null? Optional.of(course): Optional.empty();
    }

    public Optional<Course> retrieveElementByName(String course_name)
    {
        Course course = entityManager.createNamedQuery("Course.findByName", Course.class)
                .setParameter("name", course_name)
                .getSingleResult();
        return course != null ? Optional.of(course) : Optional.empty();
    }
    /*============UPDATE============*/
    public void updateElement(Course course)
    {
        Course courseToUpdate = entityManager.find(Course.class, course.getId());
        courseToUpdate.setName(course.getName());
        courseToUpdate.setDescription(course.getDescription());
        entityManager.merge(courseToUpdate);
    }
    /*============DELETE============*/
    public void deleteElement(Course course)
    {
        Course courseToDelete = entityManager.find(Course.class, course.getId());
        //entityManager.remove(courseToDelete);
        courseToDelete.setDeletedFlag(true);
        courseToDelete.setTeacher(null); //Child
        courseToDelete.setStudents(null); //Child
        entityManager.merge(courseToDelete);
    }

    /*============OTHER============*/
    /*public void setDepartment(Department department, Course course){
        Course courseToUpdate = entityManager.find(Course.class, course.getId());
        courseToUpdate.setDepartment(department);
        entityManager.merge(courseToUpdate);
    }

    public void setTeacher(Teacher teacher, Course course) {
    }

    public void addStudent(Student student, Course course)
    {
        Course courseToUpdate = entityManager.find(Course.class, id);
        courseToUpdate.getStudents().add(student);
    }

     */
}
