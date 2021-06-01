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

@WebServlet("/SaveServlet")
public class DepartmentCreateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");

        Department department=new Department();
        department.setId(id);
        department.setName(name);

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();

        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(em);
        int status=departmentService.saveDepartment(department);
        em.close();

        if(status>0){
            out.print("<p>Record saved successfully!</p>");
            request.getRequestDispatcher("index.html").include(request, response);
        }else{
            out.println("Sorry! unable to save record");
        }

        out.close();
    }
}
