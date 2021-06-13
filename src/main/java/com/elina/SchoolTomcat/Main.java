package com.elina.SchoolTomcat;

import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.service.impl.CourseServiceImpl;
import com.elina.SchoolTomcat.service.impl.DepartmentServiceImpl;
import com.elina.SchoolTomcat.util.Utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        /*
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Elina");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Department department1 = Utility.createDepartment(1,new LinkedList<>(Arrays.asList(1,2)));
        Department department2 = Utility.createDepartment(2,new LinkedList<>(Arrays.asList(3)));
        List<Department> savedDepartments = new LinkedList<>();
        savedDepartments.add(department1);
        savedDepartments.add(department2);

        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(em);
        departmentService.saveDepartment(department1);
        departmentService.saveDepartment(department2);
        System.out.println("Saved departments"+savedDepartments);
        departmentService.addCourse(1, new Course(4,"Course 4", "Classic Control"));
        System.out.println("Updated departments"+departmentService.retrieveAllDepartments());
        em.close();


        /*
        EntityManager em2 = emf.createEntityManager();
        em2.getTransaction().begin();
        CourseServiceImpl courseService = new CourseServiceImpl(em2);
        Course course = new Course(4,"Course 4", "Classic Control");
        course.setDepartment(department1);
        courseService.saveCourse(course);
        em2.close();

        EntityManager em3 = emf.createEntityManager();
        em3.getTransaction().begin();
        DepartmentServiceImpl departmentService2 = new DepartmentServiceImpl(em3);
        departmentService2.addCourse(1, course);
        System.out.println("Updated departments"+departmentService2.retrieveAllDepartments());
         */

    }
}
