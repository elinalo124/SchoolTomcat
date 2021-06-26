package com.elina.SchoolTomcat.servlet;

import com.elina.SchoolTomcat.model.Teacher;
import com.elina.SchoolTomcat.service.TeacherService;
import com.elina.SchoolTomcat.service.impl.TeacherServiceImpl;
import com.elina.SchoolTomcat.util.JPAUtil;
import com.elina.SchoolTomcat.util.Utility;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Teacher")
public class TeacherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override //CREATE
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        TeacherService teacherService = new TeacherServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        Teacher teacher = Utility.getObject(request,objectMapper, Teacher.class);

        //Store object
        int saveStatus =teacherService.saveTeacher(teacher);
        em.close();

        //Respond with created object
        if(saveStatus==1){response.setStatus(201);}
        response.getWriter().println(objectMapper.writeValueAsString(teacher));
    }

    @Override //RETRIEVE
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        TeacherService teacherService = new TeacherServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        //Determine method, retrieve and respond
        switch(request.getParameter("method")) {
            case "all":
                List<Teacher> listDep= teacherService.retrieveAllTeachers();
                response.getWriter().println(objectMapper.writeValueAsString(listDep));
                break;
            case "id":
                String id = request.getParameter("id");
                Teacher teacherById = teacherService.retrieveTeacherByID(Integer.parseInt(id)).get();
                response.getWriter().println(objectMapper.writeValueAsString(teacherById));
                break;
            case "name":
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                Teacher teacherByName = teacherService.retrieveTeacherByName(firstName,lastName).get();
                response.getWriter().println(objectMapper.writeValueAsString(teacherByName));
                break;
            default:
                System.out.println("Non existent method");
                break;
        }
        em.close();
    }

    @Override //UPDATE
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        TeacherService teacherService = new TeacherServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        Teacher teacher = Utility.getObject(request,objectMapper, Teacher.class);

        teacherService.updateTeacher(teacher);

        //List<Teacher> retrievedTeachers = teacherService.retrieveAllTeachers();
        //response.getWriter().println(objectMapper.writeValueAsString(retrievedTeachers));
        em.close();
    }

    @Override //DELETE
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        TeacherService teacherService = new TeacherServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        Teacher teacher = Utility.getObject(request,objectMapper, Teacher.class);

        teacherService.deleteTeacher(teacher);
        response.getWriter().println("Deleted by name");

        em.close();
    }
}
