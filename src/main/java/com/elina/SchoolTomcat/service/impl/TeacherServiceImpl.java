package com.elina.SchoolTomcat.service.impl;

import com.elina.SchoolTomcat.dao.CourseDAO;
import com.elina.SchoolTomcat.dao.DepartmentDAO;
import com.elina.SchoolTomcat.dao.StudentDAO;
import com.elina.SchoolTomcat.dao.TeacherDAO;
import com.elina.SchoolTomcat.dao.impl.CourseDAOImpl;
import com.elina.SchoolTomcat.dao.impl.DepartmentDAOImpl;
import com.elina.SchoolTomcat.dao.impl.StudentDAOImpl;
import com.elina.SchoolTomcat.dao.impl.TeacherDAOImpl;
import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Student;
import com.elina.SchoolTomcat.model.Teacher;
import com.elina.SchoolTomcat.service.TeacherService;
import com.elina.SchoolTomcat.util.JPASessionUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

public class TeacherServiceImpl implements TeacherService {

    private EntityManager em;
    private CourseDAO courseDAOImpl;
    private TeacherDAO teacherDAOImpl;

    public TeacherServiceImpl(EntityManager em) {
        this.em=em;
        courseDAOImpl = new CourseDAOImpl(em);
        teacherDAOImpl = new TeacherDAOImpl(em);
    }

    /*-----CREATE-----*/
    public int saveTeacher(Teacher teacher)
    {
        begin();
        try{
            teacherDAOImpl.saveElement(teacher);
            /*
            Teacher savedTeacher = teacherDAOImpl.retrieveElementByName(teacher.getFirstName(),teacher.getLastName()).get();
            //Update course with new teacher
            if(teacher.getCourse()!=null){
                Course retrievedCourse = courseDAOImpl.retrieveElementByName(teacher.getCourse().getName()).get();
                retrievedCourse.setTeacher(savedTeacher);
                courseDAOImpl.updateElement(retrievedCourse);
            }
             */
            end();
            return 1;
        }catch(PersistenceException exc){
            em.getTransaction().rollback();
            throw exc;
        }

    }



    /*-----RETRIEVE-----*/
    public List<Teacher> retrieveAllTeachers()
    {
        begin();
        return teacherDAOImpl.retrieveAllElements();
    }
    public Optional<Teacher> retrieveTeacherByID (int teacher_id)
    {
        begin();
        return teacherDAOImpl.retrieveElementByID(teacher_id);
    }
    public Optional<Teacher> retrieveTeacherByName (String firstName, String lastName)
    {
        begin();
        return teacherDAOImpl.retrieveElementByName(firstName,lastName);
    }




    /*-----UPDATE-----*/
    public void updateTeacher(Teacher teacher) //knows db's id, updates firstName/lastName/education
    {
        begin();
        try{
            teacherDAOImpl.updateElement(teacher);
            end();
        }catch(PersistenceException exc){
            em.getTransaction().rollback();
            throw exc;
        }

    }
    /*-----DELETE-----*/
    public void deleteTeacher(Teacher teacher)
    {
        begin();
        try{
            //No children deletion
            //Teacher deletion
            teacherDAOImpl.deleteElement(teacher);
            end();
        }catch(PersistenceException exc){
            em.getTransaction().rollback();
            throw exc;
        }

    }

    /*-----HELPER METHODS-----*/
    private void begin()
    {
        if(!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void end(){
        em.getTransaction().commit();
    }

}
