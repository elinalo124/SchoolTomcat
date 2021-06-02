package com.elina.SchoolTomcat.servlet;

import com.elina.SchoolTomcat.service.impl.DepartmentServiceImpl;
import com.elina.SchoolTomcat.util.JPAUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.io.IOException;

@WebServlet("/DepartmentDeleteServlet")
public class DepartmentDeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid=request.getParameter("id");
        int id=Integer.parseInt(sid);

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(em);
        departmentService.deleteDepartmentByID(id);
        response.sendRedirect("DepartmentRetrieveServlet");
    }
}
