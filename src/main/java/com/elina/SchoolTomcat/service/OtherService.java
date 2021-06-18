package com.elina.SchoolTomcat.service;

import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.model.Student;
import com.elina.SchoolTomcat.model.Teacher;

import javax.persistence.PersistenceException;
import java.util.List;

public interface OtherService {

    /*----------------------------------------ENTITY RELATIONSHIP----------------------------------------------------*/
    void addCourseToDepartment(int course_id, int department_id) ;
    void addStudentToCourse(int student_id, int course_id);
    void addTeacherToCourse(int teacher_id, int course_id);
    void removeCourseFromDepartment(int course_id, int department_id);
    void removeStudentFromCourse(int student_id, int course_id);
    void removeTeacherFromCourse(int teacher_id, int course_id);

    /*--------------------------------------BULK ADDITION AND REMOVAL-------------------------------------------------*/
    int studentBulkAdd(List<Student> students);
    int courseBulkAdd(List<Course> courses);

}
