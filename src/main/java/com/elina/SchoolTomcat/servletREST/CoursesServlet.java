package com.elina.SchoolTomcat.servletREST;

import com.elina.SchoolTomcat.filter.FilterAux;
import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.service.CourseService;
import com.elina.SchoolTomcat.service.impl.CourseServiceImpl;
import com.elina.SchoolTomcat.service.impl.DepartmentServiceImpl;
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

@WebServlet("/courses/*")
public class CoursesServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        CourseService courseService = new CourseServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        String pathInfo = request.getPathInfo();
        FilterAux filterAux = new FilterAux(pathInfo);

        switch (filterAux.getMethod()){
            case "Method 1":
                Course course = Utility.getObject(request,objectMapper, Course.class);
                int saveStatus =courseService.saveCourse(course);
                if(saveStatus==1){response.setStatus(201);}
                response.getWriter().println(objectMapper.writeValueAsString(course));
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
        CourseService courseService = new CourseServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        String pathInfo = request.getPathInfo();
        FilterAux filterAux = new FilterAux(pathInfo);

        switch (filterAux.getMethod()){
            case "Method 1":
                List<Course> listDep= courseService.retrieveAllCourses();
                response.getWriter().println(objectMapper.writeValueAsString(listDep));
                break;
            case "Method 2":
                Course courseById = courseService.retrieveCourseByID(filterAux.getId()).get();
                response.getWriter().println(objectMapper.writeValueAsString(courseById));
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
        CourseService courseService = new CourseServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        String pathInfo = request.getPathInfo();
        FilterAux filterAux = new FilterAux(pathInfo);

        switch (filterAux.getMethod()){
            case "Method 1":
                List<Course> courses = Utility.getListOfObjects(request, objectMapper, Course[].class);
                for (Course course: courses) courseService.updateCourse(course);
                break;
            case "Method 2":
                Course course = Utility.getObject(request,objectMapper, Course.class);
                course.setId(filterAux.getId());
                courseService.updateCourse(course);
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
        CourseService courseService = new CourseServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        String pathInfo = request.getPathInfo();
        FilterAux filterAux = new FilterAux(pathInfo);

        switch (filterAux.getMethod()){
            case "Method 1":
                List<Course> courses = Utility.getListOfObjects(request, objectMapper, Course[].class);
                for (Course course: courses) courseService.deleteCourse(course);
                break;
            case "Method 2":
                Course courseById = courseService.retrieveCourseByID(filterAux.getId()).get();
                courseService.deleteCourse(courseById);
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
