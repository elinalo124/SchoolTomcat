package com.elina.SchoolTomcat.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.service.impl.DepartmentServiceImpl;
import com.elina.SchoolTomcat.util.JPAUtil;
import javax.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/RetrieveDepartmentServlet")
public class DepartmentRetrieveServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("<a href='index.html'>Add New Employee</a>");
        out.println("<h1>Department List</h1>");

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(em);
        List<Department> listDep= departmentService.retrieveAllDepartments();

        out.print("<table border='1' width='100%'");
        out.print("<tr><th>Id</th><th>Name</th>");
        for(Department department:listDep){
            out.print("<tr><td>"+department.getId()+"</td><td>"+department.getName());
        }
        out.print("</table>");

        out.close();
    }
}
