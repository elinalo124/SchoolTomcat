package com.elina.SchoolTomcat.servletREST;

import com.elina.SchoolTomcat.filter.FilterAux;
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

@WebServlet("/teachers/*")
public class TeachersServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        TeacherService teacherService = new TeacherServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        String pathInfo = request.getPathInfo();
        FilterAux filterAux = new FilterAux(pathInfo);

        switch (filterAux.getMethod()){
            case "Method 1":
                Teacher teacher = Utility.getObject(request,objectMapper, Teacher.class);
                int saveStatus =teacherService.saveTeacher(teacher);
                if(saveStatus==1){response.setStatus(201);}
                response.getWriter().println(objectMapper.writeValueAsString(teacher));
                break;
            case "Method 2":
            case "error":
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        TeacherService teacherService = new TeacherServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        String pathInfo = request.getPathInfo();
        FilterAux filterAux = new FilterAux(pathInfo);

        switch (filterAux.getMethod()){
            case "Method 1":
                List<Teacher> listDep= teacherService.retrieveAllTeachers();
                response.getWriter().println(objectMapper.writeValueAsString(listDep));
                break;
            case "Method 2":
                Teacher teacherById = teacherService.retrieveTeacherByID(filterAux.getId()).get();
                response.getWriter().println(objectMapper.writeValueAsString(teacherById));
                break;
            case "error":
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                break;
            default:
                break;
        }
        em.close();
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        TeacherService teacherService = new TeacherServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        String pathInfo = request.getPathInfo();
        FilterAux filterAux = new FilterAux(pathInfo);

        switch (filterAux.getMethod()){
            case "Method 1":
                List<Teacher> teachers = Utility.getListOfObjects(request, objectMapper, Teacher[].class);
                for (Teacher teacher: teachers) teacherService.updateTeacher(teacher);
                break;
            case "Method 2":
                Teacher teacher = Utility.getObject(request,objectMapper, Teacher.class);
                teacher.setId(filterAux.getId());
                teacherService.updateTeacher(teacher);
                break;
            case "error":
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                break;
            default:
                break;
        }
        em.close();
    }

    @Override //DELETE
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        TeacherService teacherService = new TeacherServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        String pathInfo = request.getPathInfo();
        FilterAux filterAux = new FilterAux(pathInfo);

        switch (filterAux.getMethod()){
            case "Method 1":
                List<Teacher> teachers = Utility.getListOfObjects(request, objectMapper, Teacher[].class);
                for (Teacher teacher: teachers) teacherService.deleteTeacher(teacher);
                break;
            case "Method 2":
                Teacher teacherById = teacherService.retrieveTeacherByID(filterAux.getId()).get();
                teacherService.deleteTeacher(teacherById);
                break;
            case "error":
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                break;
            default:
                break;
        }
        em.close();
    }
}
