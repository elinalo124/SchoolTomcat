package com.elina.SchoolTomcat.util;

import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.model.Student;
import com.elina.SchoolTomcat.model.Teacher;

import java.util.LinkedList;
import java.util.List;

public class Utility {

    /*
     static private final String[][] COURSE_DATA = {
            {"Course 1", "Maths"},
            {"Course 2", "Physics"},
             {"Course 3", "Electronics"}
     };
    static private final String[][] DEPARTMENT_DATA = {
            {"Department 1"},
            {"Department 2"}};
    static private final String[][] STUDENT_DATA = {
            {"Elina", "Lo", "Electronic engineering"},
            {"Elisa", "Lo", "Geophysics"}};
    static private final String[][] TEACHER_DATA = {
            {"Mathematician","Eleonora","Lo"},
            {"Physician","Pepe","Pepito"}};

    public static Department createDepartment(Integer i, List<Integer> courseNums){
        List<Course> courses = new LinkedList<>();
        for (Integer courseNum: courseNums){
            courses.add(createCourse(courseNum));
        }
        return new Department(i,DEPARTMENT_DATA[i-1][0],courses);
    }

    public static Course createCourse(Integer i) {
        return new Course(i,COURSE_DATA[i-1][0],COURSE_DATA[i-1][1]);
    }

    public static Student createStudent(Integer i){
        return new Student(i,STUDENT_DATA[i-1][0],STUDENT_DATA[i-1][1],i,STUDENT_DATA[i-1][2]);
    }
    public static Teacher createTeacher(Integer i, Integer courseNum){
        Teacher teacher = new Teacher(i, TEACHER_DATA[i-1][0],createCourse(courseNum));
        teacher.setFirstName(TEACHER_DATA[i-1][1]);
        teacher.setLastName(TEACHER_DATA[i-1][2]);
        return teacher;
    }

     */

}
