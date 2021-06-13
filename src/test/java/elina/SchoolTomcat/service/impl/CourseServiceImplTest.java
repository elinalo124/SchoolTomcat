package elina.SchoolTomcat.service.impl;

import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Student;
import com.elina.SchoolTomcat.service.impl.CourseServiceImpl;
import com.elina.SchoolTomcat.util.Utility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceImplTest {

    /*
    private Course course1;
    private Course course2;
    private List<Course> savedCourses = new LinkedList<>();
    private List<Course> retrievedCourses;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Elina");
    private CourseServiceImpl courseService;
    private EntityManager em;

    @BeforeEach
    public void init() {
        initCourses();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        courseService = new CourseServiceImpl(em);
        courseService.saveCourse(course1);
        courseService.saveCourse(course2);

        retrievedCourses = courseService.retrieveAllCourses();

        em.getTransaction().commit();

    }


    @Test
    @DisplayName("C")
    public void createCourseTest()
    {
        assertEquals(course1.getName(), retrievedCourses.get(0).getName());
    }

    @Test
    @DisplayName("R")
    public void retrieveCoursesTest()
    {
        assertEquals(savedCourses, retrievedCourses);
        assertEquals(course1, courseService.retrieveCourseByID(1).get());
        assertEquals(course1, courseService.retrieveCourseByName("Course 1").get());
    }

    @Test
    @DisplayName("U")
    public void updateCourseTest()
    {
        courseService.addStudent(1, new Student(1, "Elina", "Lo", 1, "Electronic engineering"));
        assertEquals(course1.getStudents(), courseService.retrieveCourseByID(1).get().getStudents());
    }

    @Test
    @DisplayName("D")
    public void deleteCourseTest()
    {
        courseService.deleteCourse(course2);
        List<Course> retrievedCourses = courseService.retrieveAllCourses();
        assertEquals(1, retrievedCourses.size());
    }

    @AfterEach
    public void dropDown() {
        em.clear();
        em.close();
    }

    private void initCourses()
    {
        course1 = Utility.createCourse(1);
        course2 = Utility.createCourse(2);
        savedCourses.add(course1);
        savedCourses.add(course2);
    }

     */

}