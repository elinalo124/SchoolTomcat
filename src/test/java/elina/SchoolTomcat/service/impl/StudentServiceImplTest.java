package elina.SchoolTomcat.service.impl;

import com.elina.SchoolTomcat.model.Student;
import com.elina.SchoolTomcat.service.impl.StudentServiceImpl;
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

class StudentServiceImplTest {
    /*
    private Student student1;
    private Student student2;
    private List<Student> savedStudents = new LinkedList<>();
    private List<Student> retrievedStudents;
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Elina");
    private StudentServiceImpl studentService;
    private EntityManager em;

    @BeforeEach
    public void init() {
        initStudents();

        em = emf.createEntityManager();
        em.getTransaction().begin();

        studentService = new StudentServiceImpl(em);
        studentService.saveStudent(student1);
        studentService.saveStudent(student2);

        retrievedStudents = studentService.retrieveAllStudents();

        em.getTransaction().commit();

    }


    @Test
    @DisplayName("C")
    public void createStudentTest()
    {
        assertEquals(student1.getFirstName(), retrievedStudents.get(0).getFirstName());
    }

    @Test
    @DisplayName("R")
    public void retrieveStudentsTest()
    {
        assertEquals(savedStudents, retrievedStudents);
        assertEquals(student1, studentService.retrieveStudentByID(1).get());
    }

    @Test
    @DisplayName("U")
    public void updateStudentTest()
    {
        String newMajor = "Fashion design";
        studentService.changeMajor(1, newMajor);
        assertEquals(newMajor, studentService.retrieveStudentByID(1).get().getMajor());
    }

    @Test
    @DisplayName("D")
    public void deleteStudentTest()
    {
        studentService.deleteStudent(student1);
        List<Student> retrievedStudents = studentService.retrieveAllStudents();
        assertEquals(1, retrievedStudents.size());
    }

    @AfterEach
    public void dropDown() {
        em.clear();
        em.close();
    }

    private void initStudents()
    {
        student1 = Utility.createStudent(1);
        student2 = Utility.createStudent(2);
        savedStudents.add(student1);
        savedStudents.add(student2);
    }

    /*
    @Test
    @DisplayName("C")
    public void saveStudentTest()
    {
        StudentServiceImpl studentService = new StudentServiceImpl();
        // Create two department and add 2 courses to their list of courses
        Student student1 = Utility.createStudent1();
        Student student2 = Utility.createStudent2();
        studentService.saveStudent(student1);
        studentService.saveStudent(student2);
        List<Student> students = studentService.retrieveAllStudents();
        assertEquals(student1.getFirstName(), students.get(0).getFirstName());
    }


    @Test
    @DisplayName("R")
    public void getStudentTest()
    {
        StudentServiceImpl studentService = new StudentServiceImpl();
        // Create two department and add 2 courses to their list of courses
        Student student1 = Utility.createStudent1();
        Student student2 = Utility.createStudent2();
        studentService.saveStudent(student1);
        studentService.saveStudent(student2);
        List<Student> savedStudents= new LinkedList<>();
        savedStudents.add(student1);
        savedStudents.add(student2);
        List<Student> retrievedStudents = studentService.retrieveAllStudents();
        assertEquals(savedStudents, retrievedStudents);
        assertEquals(student1, studentService.retrieveStudentByID(1).get());
    }

    @Test
    @DisplayName("U")
    public void changeMajorTest()
    {
        StudentServiceImpl studentService = new StudentServiceImpl();
        // Create two department and add 2 courses to their list of courses
        Student student1 = Utility.createStudent1();
        Student student2 = Utility.createStudent2();
        studentService.saveStudent(student1);
        studentService.saveStudent(student2);
        String newMajor = "Fashion design";
        studentService.changeMajor(1, newMajor);
        assertEquals(newMajor, studentService.retrieveStudentByID(1).get().getMajor());
    }

    @Test
    @DisplayName("D")
    public void deleteCourseTest()
    {
        StudentServiceImpl studentService = new StudentServiceImpl();
        // Create two department and add 2 courses to their list of courses
        Student student1 = Utility.createStudent1();
        Student student2 = Utility.createStudent2();
        studentService.saveStudent(student1);
        studentService.saveStudent(student2);
        studentService.deleteStudent(student1);
        List<Student> retrievedStudents = studentService.retrieveAllStudents();
        assertEquals(1, retrievedStudents.size());
    }

     */

}