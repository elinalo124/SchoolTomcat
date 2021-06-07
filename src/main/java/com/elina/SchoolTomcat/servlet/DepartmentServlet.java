package com.elina.SchoolTomcat.servlet;

import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.service.impl.DepartmentServiceImpl;
import com.elina.SchoolTomcat.util.JPAUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
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

    @Override //CREATE
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        //Get Object
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Department department = objectMapper.readValue(json, Department.class);

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

        //Determine method, retrieve and respond
        switch(request.getParameter("method")) {
            case "department":
                //String json = inputStreamToString(request.getInputStream());
                String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                System.out.println("EL TEXTO LEIDO ES: "+json);
                Department department = objectMapper.readValue(json, Department.class);
                System.out.println("EL DEPTO LEIDO ES: "+department);
                int status1 = departmentService.updateDepartment(department);
                System.out.println("EL DEPTO STAUTS ES: "+status1);
                List<Department> retrievedDepartments = departmentService.retrieveAllDepartments();
                System.out.println("ALL DEPARTMENTS:"+retrievedDepartments);
                response.getWriter().println(objectMapper.writeValueAsString(retrievedDepartments));
                break;
            case "id":
                String id = request.getParameter("id");
                String name = request.getParameter("name");

                int status2 = departmentService.updateDepartmentByID(Integer.parseInt(id),name);

                response.getWriter().println(objectMapper.writeValueAsString(departmentService.retrieveAllDepartments()));
                break;
            case "addCourse":
                String id2 = request.getParameter("id");
                String json2 = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                System.out.println("EL TEXTO LEIDO ES: "+json2);
                Course course = objectMapper.readValue(json2, Course.class);
                System.out.println("EL COURSE LEIDO ES: "+course);
                departmentService.addCourse(Integer.parseInt(id2),course);
                List<Department> retrievedDepartments2 = departmentService.retrieveAllDepartments();
                System.out.println("ALL DEPARTMENTS:"+retrievedDepartments2);
                response.getWriter().println(objectMapper.writeValueAsString(retrievedDepartments2));
                break;
            default:
                System.out.println("Non existent method");
                break;
        }
        em.close();
    }

    @Override //DELETE
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        //Determine method, retrieve and respond
        switch(request.getParameter("method")) {
            case "id":
                String id = request.getParameter("id");
                departmentService.deleteDepartmentByID(Integer.parseInt(id));
                response.getWriter().println("Deleted by id");
                break;
            case "department":
                String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                Department department = objectMapper.readValue(json, Department.class);
                departmentService.deleteDepartment(department);
                response.getWriter().println("Deleted by name");
                break;
            default:
                System.out.println("Non existent method");
                break;
        }

        em.close();
    }

    /*
    private static String inputStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        return scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
    }
     */

}
