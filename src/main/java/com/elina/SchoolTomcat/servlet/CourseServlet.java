package com.elina.SchoolTomcat.servlet;

import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.service.impl.CourseServiceImpl;
import com.elina.SchoolTomcat.service.impl.DepartmentServiceImpl;
import com.elina.SchoolTomcat.util.JPAUtil;
import com.elina.SchoolTomcat.util.Utility;
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

@WebServlet("/Course")
public class CourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override //CREATE
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        CourseServiceImpl courseService = new CourseServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        //Get Object
        Course course = Utility.getObject(request, objectMapper, Course.class);

        //Store object
        int saveStatus = courseService.saveCourse(course);
        em.close();

        //Respond with created object
        if(saveStatus==1){response.setStatus(201);}
        response.getWriter().println(objectMapper.writeValueAsString(course));
    }

    @Override //RETRIEVE
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        CourseServiceImpl courseService = new CourseServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        //Determine method, retrieve and respond
        switch(request.getParameter("method")) {
            case "all":
                List<Course> listCourse= courseService.retrieveAllCourses();
                response.getWriter().println(objectMapper.writeValueAsString(listCourse));
                break;
            case "id":
                String id = request.getParameter("id");
                Course courseById = courseService.retrieveCourseByID(Integer.parseInt(id)).get();
                response.getWriter().println(objectMapper.writeValueAsString(courseById));
                break;
            case "name":
                String name = request.getParameter("name");
                Course courseById2 = courseService.retrieveCourseByName(name).get();
                response.getWriter().println(objectMapper.writeValueAsString(courseById2));
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
        CourseServiceImpl courseService = new CourseServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        Course course = Utility.getObject(request, objectMapper, Course.class);

        courseService.updateCourse(course);
        List<Course> retrievedCourses = courseService.retrieveAllCourses();
        response.getWriter().println(objectMapper.writeValueAsString(retrievedCourses));
        em.close();
    }

    @Override //DELETE
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        CourseServiceImpl courseService = new CourseServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        Course course = Utility.getObject(request, objectMapper, Course.class);

        courseService.deleteCourse(course);
        response.getWriter().println("Deleted by name");

        em.close();
    }


}
