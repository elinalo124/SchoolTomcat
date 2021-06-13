import com.elina.SchoolTomcat.model.Department;
import com.elina.SchoolTomcat.service.impl.CourseServiceImpl;
import com.elina.SchoolTomcat.service.impl.DepartmentServiceImpl;
import com.elina.SchoolTomcat.util.Utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.LinkedList;



public class CreateDB {

    public static void main(String[] args){
        /*

        Department department1 = Utility.createDepartment(1,new LinkedList<>(Arrays.asList(1,2)));
        Department department2 = Utility.createDepartment(2,new LinkedList<>(Arrays.asList(3)));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Elina");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(em);

        departmentService.saveDepartment(department1);
        departmentService.saveDepartment(department2);

        em.getTransaction().commit();

         */
    }
}
