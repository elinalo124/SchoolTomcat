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

@WebServlet("/DepartmentUpdateServlet2")
public class DepartmentUpdateServlet2 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        String sid=request.getParameter("id");
        int id=Integer.parseInt(sid);
        String name=request.getParameter("name");

        Department department=new Department();
        department.setId(id);
        department.setName(name);

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(em);
        int status=departmentService.updateDepartmentByID(id,name);

        if(status>0){
            response.sendRedirect("DepartmentRetrieveServlet");
        }else{
            out.println("Sorry! unable to update record");
        }

        out.close();
    }
}
