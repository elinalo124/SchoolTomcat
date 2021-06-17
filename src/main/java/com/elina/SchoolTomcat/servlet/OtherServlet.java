package com.elina.SchoolTomcat.servlet;

import com.elina.SchoolTomcat.service.impl.OtherServiceImpl;
import com.elina.SchoolTomcat.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Other")
public class OtherServlet extends HttpServlet {
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
