package elina.SchoolTomcat.service.impl;

import com.elina.SchoolTomcat.model.Course;
import com.elina.SchoolTomcat.model.Teacher;
import com.elina.SchoolTomcat.service.impl.TeacherServiceImpl;
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

class TeacherServiceImplTest {
    /*
    private Teacher teacher1;
    private Teacher teacher2;
    private List<Teacher> savedTeachers = new LinkedList<>();
    private List<Teacher> retrievedTeachers;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Elina");
    private TeacherServiceImpl teacherService;
    private EntityManager em;

    @BeforeEach
    public void init() {
        initTeachers();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        teacherService = new TeacherServiceImpl(em);
        teacherService.saveTeacher(teacher1);
        teacherService.saveTeacher(teacher2);

        retrievedTeachers = teacherService.retrieveAllTeachers();

        em.getTransaction().commit();

    }


    @Test
    @DisplayName("C")
    public void createTeacherTest()
    {
        assertEquals(teacher1.getEducation(), retrievedTeachers.get(0).getEducation());
    }

    @Test
    @DisplayName("R")
    public void retrieveTeachersTest()
    {
        assertEquals(savedTeachers, retrievedTeachers);
        assertEquals(teacher1, teacherService.retrieveTeacherByID(1).get());
    }

    @Test
    @DisplayName("U")
    public void updateTeacherTest()
    {
        Course newCourse= new Course(4,"Course 4", "Electric Machines");
        teacherService.saveCourse(newCourse);

        teacher1.setCourse(newCourse);
        teacherService.updateTeacher(teacher1);
        assertEquals(newCourse, teacherService.retrieveTeacherByID(1).get().getCourse());
    }

    @Test
    @DisplayName("D")
    public void deleteTeacherTest()
    {
        teacherService.deleteTeacher(teacher1);
        List<Teacher> retrievedTeachers = teacherService.retrieveAllTeachers();
        assertEquals(1, retrievedTeachers.size());
    }

    @AfterEach
    public void dropDown() {
        em.clear();
        em.close();
    }

    private void initTeachers()
    {
        teacher1 = Utility.createTeacher(1,1);
        teacher2 = Utility.createTeacher(2,2);
        savedTeachers.add(teacher1);
        savedTeachers.add(teacher2);
    }
    /*
    @Test
    @DisplayName("C")
    public void saveStudentTest()
    {
        TeacherServiceImpl teacherService = new TeacherServiceImpl();
        // Create two department and add 2 courses to their list of courses
        Teacher teacher1 = Utility.createTeacher1();
        Teacher teacher2 = Utility.createTeacher2();
        teacherService.saveTeacher(teacher1);
        teacherService.saveTeacher(teacher2);
        List<Teacher> teachers = teacherService.retrieveAllTeachers();
        assertEquals(teacher1.getEducation(), teachers.get(0).getEducation());
    }


    @Test
    @DisplayName("R")
    public void getStudentTest()
    {
        TeacherServiceImpl teacherService = new TeacherServiceImpl();
        // Create two department and add 2 courses to their list of courses
        Teacher teacher1 = Utility.createTeacher1();
        Teacher teacher2 = Utility.createTeacher2();
        teacherService.saveTeacher(teacher1);
        teacherService.saveTeacher(teacher2);
        List<Teacher> savedTeachers= new LinkedList<>();
        savedTeachers.add(teacher1);
        savedTeachers.add(teacher2);
        List<Teacher> retrievedTeachers = teacherService.retrieveAllTeachers();
        assertEquals(savedTeachers, retrievedTeachers);
        assertEquals(teacher1, teacherService.retrieveTeacherByID(1).get());
    }

    @Test
    @DisplayName("U")
    public void changeMajorTest()
    {
        //CourseServiceImpl courseService = new CourseServiceImpl();


        //courseService.saveCourse(newCourse);

        TeacherServiceImpl teacherService = new TeacherServiceImpl();
        // Create two department and add 2 courses to their list of courses
        Teacher teacher1 = Utility.createTeacher1();
        Teacher teacher2 = Utility.createTeacher2();
        teacherService.saveTeacher(teacher1);
        teacherService.saveTeacher(teacher2);

        Course newCourse= new Course(4,"Course 4", "Electric Machines");
        teacherService.saveCourse(newCourse);

        teacher1.setCourse(newCourse);
        teacherService.updateTeacher(teacher1);
        assertEquals(newCourse, teacherService.retrieveTeacherByID(1).get().getCourse());
    }

    @Test
    @DisplayName("D")
    public void deleteCourseTest()
    {
        TeacherServiceImpl teacherService = new TeacherServiceImpl();
        // Create two department and add 2 courses to their list of courses
        Teacher teacher1 = Utility.createTeacher1();
        Teacher teacher2 = Utility.createTeacher2();
        teacherService.saveTeacher(teacher1);
        teacherService.saveTeacher(teacher2);
        teacherService.deleteTeacher(teacher1);
        List<Teacher> retrievedTeachers = teacherService.retrieveAllTeachers();
        assertEquals(1, retrievedTeachers.size());
    }

     */

}