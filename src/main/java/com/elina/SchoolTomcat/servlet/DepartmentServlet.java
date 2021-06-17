package com.elina.SchoolTomcat.servlet;

import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.service.impl.DepartmentServiceImpl;
import com.elina.SchoolTomcat.util.JPAUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet("/Department")
public class DepartmentServlet extends HttpServlet {

    /*
    * CREATE= POST
    * READ= GET (safe)
    * UPDATE= PUT (Idepotent)
    * DELETE= DELETE (Idepotent)
    * */

    private static final long serialVersionUID = 1L;

    /*
    private static Object getObject(HttpServletRequest request, ObjectMapper objectMapper) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        return objectMapper.readValue(json, Object.class);
    }

     */

    @Override //CREATE
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        //Get Object
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Department department = objectMapper.readValue(json, Department.class);

        //Department department = (Department) getObject(request,objectMapper);
        //Store object
        int status=departmentService.saveDepartment(department);
        em.close();

        //Respond with created object
        response.getWriter().println(objectMapper.writeValueAsString(department));
    }

    @Override //RETRIEVE
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        //Determine method, retrieve and respond
        switch(request.getParameter("method")) {
            case "all":
                List<Department> listDep= departmentService.retrieveAllDepartments();
                response.getWriter().println(objectMapper.writeValueAsString(listDep));
                break;
            case "id":
                String id = request.getParameter("id");
                Department departmentById = departmentService.retrieveDepartmentByID(Integer.parseInt(id)).get();
                response.getWriter().println(objectMapper.writeValueAsString(departmentById));
                break;
            case "name":
                String name = request.getParameter("name");
                Department departmentByName = departmentService.retrieveDepartmentByName(name).get();
                response.getWriter().println(objectMapper.writeValueAsString(departmentByName));
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
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Department department = objectMapper.readValue(json, Department.class);
        int status1 = departmentService.updateDepartment(department);
        List<Department> retrievedDepartments = departmentService.retrieveAllDepartments();
        response.getWriter().println(objectMapper.writeValueAsString(retrievedDepartments));
        em.close();
    }

    @Override //DELETE
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Department department = objectMapper.readValue(json, Department.class);
        departmentService.deleteDepartment(department);
        response.getWriter().println("Deleted by name");

        em.close();
    }


}
