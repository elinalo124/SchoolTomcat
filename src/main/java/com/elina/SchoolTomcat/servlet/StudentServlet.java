package com.elina.SchoolTomcat.servlet;

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

@WebServlet("/Student")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override //CREATE
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        StudentService studentService = new StudentServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        Student student = Utility.getObject(request,objectMapper, Student.class);

        //Store object
        int saveStatus =studentService.saveStudent(student);
        em.close();

        //Respond with created object
        if(saveStatus==1){response.setStatus(201);}
        response.getWriter().println(objectMapper.writeValueAsString(student));
    }

    @Override //RETRIEVE
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        StudentService studentService = new StudentServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        //Determine method, retrieve and respond
        switch(request.getParameter("method")) {
            case "all":
                List<Student> listDep= studentService.retrieveAllStudents();
                response.getWriter().println(objectMapper.writeValueAsString(listDep));
                break;
            case "id":
                String id = request.getParameter("id");
                Student studentById = studentService.retrieveStudentByID(Integer.parseInt(id)).get();
                response.getWriter().println(objectMapper.writeValueAsString(studentById));
                break;
            case "name":
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                Student studentByName = studentService.retrieveStudentByName(firstName,lastName).get();
                response.getWriter().println(objectMapper.writeValueAsString(studentByName));
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
        StudentService studentService = new StudentServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        Student student = Utility.getObject(request,objectMapper, Student.class);

        studentService.updateStudent(student);

        //List<student> retrievedstudents = studentService.retrieveAllstudents();
        //response.getWriter().println(objectMapper.writeValueAsString(retrievedstudents));
        em.close();
    }

    @Override //DELETE
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        StudentService studentService = new StudentServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        Student student = Utility.getObject(request,objectMapper, Student.class);

        studentService.deleteStudent(student);
        response.getWriter().println("Deleted by name");

        em.close();
    }
}
