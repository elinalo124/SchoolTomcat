package com.elina.SchoolTomcat.servlet;

import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.service.impl.DepartmentServiceImpl;
import com.elina.SchoolTomcat.util.JPAUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/DepartmentUpdateServlet")
public class DepartmentUpdateServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("<h1>Update Department</h1>");
        String sid=request.getParameter("id");
        int id=Integer.parseInt(sid);

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(em);
        Department department = departmentService.retrieveDepartmentByID(id).get();

        out.print("<form action='DepartmentUpdateServlet2' method='GET'>");
        out.print("<table>");
        out.print("<tr><td></td><td><input type='hidden' name='id' value='"+department.getId()+"'/></td></tr>");
        out.print("<tr><td>Name:</td><td><input type='text' name='name' value='"+department.getName()+"'/></td></tr>");

        out.print("<tr><td colspan='2'><input type = 'submit' value = 'Submit' /></td></tr>");
        out.print("</table>");
        out.print("</form>");

        out.close();
    }
}
