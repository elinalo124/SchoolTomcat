package com.elina.SchoolTomcat.service.impl;

import com.elina.SchoolTomcat.dao.CourseDAO;
import com.elina.SchoolTomcat.dao.TeacherDAO;
import com.elina.SchoolTomcat.dao.impl.CourseDAOImpl;
import com.elina.SchoolTomcat.dao.impl.DepartmentDAOImpl;
import com.elina.SchoolTomcat.dao.impl.TeacherDAOImpl;
import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Teacher;
import com.elina.SchoolTomcat.service.TeacherService;
import com.elina.SchoolTomcat.util.JPASessionUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class TeacherServiceImpl implements TeacherService {

    private EntityManager em;
    private TeacherDAOImpl teacherDAOImpl;
    private CourseDAOImpl courseDAOImpl;

    public TeacherServiceImpl(EntityManager em) {
        this.em=em;
        teacherDAOImpl = new TeacherDAOImpl(em);
        courseDAOImpl = new CourseDAOImpl(em);
    }

    /*-----CREATE-----*/
    public void saveTeacher(Teacher teacher)
    {
        courseDAOImpl.saveElement(teacher.getCourse());
        teacherDAOImpl.saveElement(teacher);
    }
    /*-----RETRIEVE-----*/
    public List<Teacher> retrieveAllTeachers()
    {
        return teacherDAOImpl.retrieveAllElements();
    }
    public Optional<Teacher> retrieveTeacherByID (int id)
    {
        return teacherDAOImpl.retrieveElementByID(id);
    }
    /*-----UPDATE-----*/
    public void saveCourse(Course course){
        courseDAOImpl.saveElement(course);
    }
    public void updateTeacher(Teacher teacher)
    {
        teacherDAOImpl.updateElement(teacher);
    }
    /*-----DELETE-----*/
    public void deleteTeacher(Teacher teacher)
    {
        teacherDAOImpl.deleteElement(teacher);
    }


}
