package com.elina.SchoolTomcat.servletREST;

import com.elina.SchoolTomcat.filter.FilterAux;
import com.elina.SchoolTomcat.model.Student;
import com.elina.SchoolTomcat.service.StudentService;
import com.elina.SchoolTomcat.service.impl.StudentServiceImpl;
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

@WebServlet("/students/*")
public class StudentsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        StudentService studentService = new StudentServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        String pathInfo = request.getPathInfo();
        FilterAux filterAux = new FilterAux(pathInfo);

        switch (filterAux.getMethod()){
            case "Method 1":
                Student student = Utility.getObject(request,objectMapper, Student.class);
                int saveStatus =studentService.saveStudent(student);
                if(saveStatus==1){response.setStatus(201);}
                response.getWriter().println(objectMapper.writeValueAsString(student));
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
        StudentService studentService = new StudentServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        String pathInfo = request.getPathInfo();
        FilterAux filterAux = new FilterAux(pathInfo);

        switch (filterAux.getMethod()){
            case "Method 1":
                List<Student> listDep= studentService.retrieveAllStudents();
                response.getWriter().println(objectMapper.writeValueAsString(listDep));
                break;
            case "Method 2":
                Student studentById = studentService.retrieveStudentByID(filterAux.getId()).get();
                response.getWriter().println(objectMapper.writeValueAsString(studentById));
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
        StudentService studentService = new StudentServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        String pathInfo = request.getPathInfo();
        FilterAux filterAux = new FilterAux(pathInfo);

        switch (filterAux.getMethod()){
            case "Method 1":
                List<Student> students = Utility.getListOfObjects(request, objectMapper, Student[].class);
                for (Student student: students) studentService.updateStudent(student);
                break;
            case "Method 2":
                Student student = Utility.getObject(request,objectMapper, Student.class);
                student.setId(filterAux.getId());
                studentService.updateStudent(student);
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
        StudentService studentService = new StudentServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        String pathInfo = request.getPathInfo();
        FilterAux filterAux = new FilterAux(pathInfo);

        switch (filterAux.getMethod()){
            case "Method 1":
                List<Student> students = Utility.getListOfObjects(request, objectMapper, Student[].class);
                for (Student student: students) studentService.deleteStudent(student);
                break;
            case "Method 2":
                Student studentById = studentService.retrieveStudentByID(filterAux.getId()).get();
                studentService.deleteStudent(studentById);
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
