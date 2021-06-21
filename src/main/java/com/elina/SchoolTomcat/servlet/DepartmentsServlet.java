package com.elina.SchoolTomcat.servlet;

import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.service.CourseService;
import com.elina.SchoolTomcat.service.OtherService;
import com.elina.SchoolTomcat.service.impl.CourseServiceImpl;
import com.elina.SchoolTomcat.service.impl.DepartmentServiceImpl;
import com.elina.SchoolTomcat.service.impl.OtherServiceImpl;
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
import org.apache.commons.lang3.math.NumberUtils;

@WebServlet("/departments/*")
public class DepartmentsServlet extends HttpServlet {

    /*CREATE
    /departments            creates new department
    /departments/1          error
    /department/1/course    create a new course for department 1 add it if it does not exist
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        String pathInfo = request.getPathInfo(); //Does not take departments into consideration
        System.out.println("PATH READ "+pathInfo);

        /*---/departments---*/
        if(pathInfo==null){
            Department department = Utility.getObject(request,objectMapper, Department.class);
            //Store object
            int saveStatus =departmentService.saveDepartment(department);
            em.close();
            //Respond with created object
            if(saveStatus==1){response.setStatus(201);}
            response.getWriter().println(objectMapper.writeValueAsString(department));
        }

        /*---/departments/{id}/course---*/
        else{
            //Check Syntax
            String[] parts = pathInfo.substring(1).split("/");
            if(NumberUtils.isNumber(parts[0]) && parts[1].equals("course") && parts.length==2){

                //Read course
                Course courseToAdd = Utility.getObject(request,objectMapper, Course.class);

                //Create course if it doesnt exist
                CourseService courseService = new CourseServiceImpl(em);
                if(!courseService.retrieveCourseByID(courseToAdd.getId()).isPresent())
                    courseService.saveCourse(courseToAdd);

                //Add course to Department
                OtherService otherService = new OtherServiceImpl(em);
                otherService.addCourseToDepartment(courseToAdd.getId(),Integer.parseInt(parts[0]));
            }
            else response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    /*RETRIEVE
    /departments            retrieves all departments
    /departments/1          retrieves the first department
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        String pathInfo = request.getPathInfo();

        if(pathInfo==null){
            List<Department> listDep= departmentService.retrieveAllDepartments();
            response.getWriter().println(objectMapper.writeValueAsString(listDep));
        }
        else{
            String id = pathInfo.substring(1);
            if(NumberUtils.isNumber(id)){
                Department departmentById = departmentService.retrieveDepartmentByID(Integer.parseInt(id)).get();
                response.getWriter().println(objectMapper.writeValueAsString(departmentById));
            }
            else response.sendError(HttpServletResponse.SC_BAD_REQUEST);

        }
        em.close();
    }

    /*UPDATE
    /departments            bulk update all departments
    /departments/1          update the first department
    */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        String pathInfo = request.getPathInfo();

        if(pathInfo==null){
            List<Department> departments = Utility.getListOfObjects(request, objectMapper, Department[].class);
            for (Department department: departments) departmentService.updateDepartment(department);
        }
        else{
            String id = pathInfo.substring(1);
            if(NumberUtils.isNumber(id)){
                Department department = Utility.getObject(request,objectMapper, Department.class);
                department.setId(Integer.parseInt(id));
                departmentService.updateDepartment(department);
            } else response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        em.close();
    }

    /*DELETE
    /departments            bulk delete departments
    /departments/1          remove department 1
    */
    @Override //DELETE
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();

        String pathInfo = request.getPathInfo();

        if(pathInfo==null){
            List<Department> departments = Utility.getListOfObjects(request, objectMapper, Department[].class);
            for (Department department: departments) departmentService.deleteDepartment(department);
        }
        else{
            String id = pathInfo.substring(1);
            if(NumberUtils.isNumber(id)){
                Department departmentById = departmentService.retrieveDepartmentByID(Integer.parseInt(id)).get();
                departmentService.deleteDepartment(departmentById);
            } else response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        em.close();
    }
}
