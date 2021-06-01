package com.elina.SchoolTomcat.service.impl;

import com.elina.SchoolTomcat.dao.impl.StudentDAOImpl;
import com.elina.SchoolTomcat.model.Student;
import com.elina.SchoolTomcat.service.StudentService;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentService{
    private EntityManager em;
    private StudentDAOImpl studentDAOImpl;
    //private CourseDAOImpl courseDAOImpl;

    public StudentServiceImpl(EntityManager em) {
        this.em=em;
        studentDAOImpl = new StudentDAOImpl(em);
        //courseDAOImpl = new CourseDAOImpl(em);
    }
    /*-----CREATE-----*/
    public void saveStudent(Student student)
    {
        studentDAOImpl.saveElement(student);
    }
    /*-----RETRIEVE-----*/
    public List<Student> retrieveAllStudents()
    {
        return studentDAOImpl.retrieveAllElements();
    }
    public Optional<Student> retrieveStudentByID (int id)
    {
        return studentDAOImpl.retrieveElementByID(id);
    }
    /*-----UPDATE-----*/
    public void updateStudent(Student student)
    {
        studentDAOImpl.updateElement(student);
    }
    /*-----DELETE-----*/
    public void deleteStudent(Student student)
    {
        studentDAOImpl.deleteElement(student);
    }
    /*-----OTHER-----*/
    public void changeMajor(Integer id, String major)
    {
        studentDAOImpl.changeMajor(id, major);
    }


}
