package com.elina.SchoolTomcat.servlet;

import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.service.impl.CourseServiceImpl;
import com.elina.SchoolTomcat.service.impl.DepartmentServiceImpl;
import com.elina.SchoolTomcat.util.JPAUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CourseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override //CREATE
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        CourseServiceImpl courseService = new CourseServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        //Get Object
        String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Course course = objectMapper.readValue(json, Course.class);

        //Store object
        courseService.saveCourse(course);
        em.close();

        //Respond with created object
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

    /*@Override //UPDATE
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        CourseServiceImpl courseService = new CourseServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        //Determine method, retrieve and respond
        switch(request.getParameter("method")) {
            case "course":
                String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                //System.out.println("EL TEXTO LEIDO ES: "+json);
                Course course = objectMapper.readValue(json, Course.class);
                //System.out.println("EL DEPTO LEIDO ES: "+department);
                courseService.updateCourse(course);
                //System.out.println("EL DEPTO STAUTS ES: "+status1);
                List<Course> retrievedCourses = courseService.retrieveAllCourses();
                //System.out.println("ALL DEPARTMENTS:"+retrievedDepartments);
                response.getWriter().println(objectMapper.writeValueAsString(retrievedCourses));
                break;
            case "addStudent":
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

     */

    /*@Override //DELETE
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        CourseServiceImpl courseService = new CourseServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

         String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
         Course course = objectMapper.readValue(json, Course.class);
         courseService.deleteCourse(course);
         em.close();
    }

     */
}
