package com.elina.SchoolTomcat.servlet;

import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.model.Student;
import com.elina.SchoolTomcat.service.OtherService;
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

@WebServlet("/Other")
public class OtherServlet extends HttpServlet {


    @Override //CREATE
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        OtherService otherService = new OtherServiceImpl(em);
        ObjectMapper objectMapper = new ObjectMapper();
        int saveStatus = 0;

        switch (request.getParameter("method")){
            case "studentBulkAdd":
                List<Student> students = Utility.getListOfObjects(request, objectMapper, Student[].class);
                saveStatus = otherService.studentBulkAdd(students);
                break;
            case "courseBulkAdd":
                List<Course> courses = Utility.getListOfObjects(request, objectMapper, Course[].class);
                saveStatus = otherService.courseBulkAdd(courses);
                break;
            default:
                break;
        }
        em.close();
        if(saveStatus==1){response.setStatus(201);}

    }


    @Override //UPDATE
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        OtherServiceImpl otherService = new OtherServiceImpl(em);

        switch (request.getParameter("method")){
            case "addCourseToDepartment":
                int addedCourseToDep = Integer.parseInt(request.getParameter("course_id"));
                int depToAddCourse = Integer.parseInt(request.getParameter("department_id"));
                otherService.addCourseToDepartment(addedCourseToDep,depToAddCourse);
                break;
            case "addStudentToCourse":
                int addedStudentToCourse = Integer.parseInt(request.getParameter("student_id"));
                int courseToAddStudent = Integer.parseInt(request.getParameter("course_id"));
                otherService.addStudentToCourse(addedStudentToCourse,courseToAddStudent);
                break;
            case "addTeacherToCourse":
                int addedTeacherToCourse = Integer.parseInt(request.getParameter("teacher_id"));
                int courseToAddTeacher = Integer.parseInt(request.getParameter("course_id"));
                otherService.addTeacherToCourse(addedTeacherToCourse,courseToAddTeacher);
                break;
            case "removeCourseFromDepartment":
                int removedCourseFromDep = Integer.parseInt(request.getParameter("course_id"));
                int depToRemoveCourse = Integer.parseInt(request.getParameter("department_id"));
                otherService.removeCourseFromDepartment(removedCourseFromDep,depToRemoveCourse);
                break;
            case "removeStudentFromCourse":
                int removedStudentFromCourse = Integer.parseInt(request.getParameter("student_id"));
                int courseToRemoveStudent = Integer.parseInt(request.getParameter("course_id"));
                otherService.removeStudentFromCourse(removedStudentFromCourse,courseToRemoveStudent);
                break;
            case "removeTeacherFromCourse":
                int removedTeacherFromCourse = Integer.parseInt(request.getParameter("teacher_id"));
                int courseToRemoveTeacher = Integer.parseInt(request.getParameter("course_id"));
                otherService.removeTeacherFromCourse(removedTeacherFromCourse,courseToRemoveTeacher);
                break;
            default:
                break;
        }
        em.close();
    }
}
