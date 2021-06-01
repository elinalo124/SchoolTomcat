package elina.SchoolTomcat.util;

import com.elina.SchoolTomcat.util.JPASessionUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

class JPASessionUtilTest {

    @Test
    public void getEntityManager() {
        EntityManager em = JPASessionUtil.getEntityManager("Elina");
        em.close();
    }

    @Test()
    public void nonexistentEntityManagerName() {
        Exception exception = assertThrows(
                javax.persistence.PersistenceException.class,
                () -> JPASessionUtil.getEntityManager("nonexistent")
        );
        //System.out.print(exception.getMessage());
        assertTrue(exception.getMessage().contains("No Persistence provider for EntityManager named nonexistent"));
    }


    @Test
    public void getSession() {
        Session session = JPASessionUtil.getSession("Elina");
        session.close();
    }
    @Test()
    public void nonexistentSessionName() {
        Exception exception = assertThrows(
                javax.persistence.PersistenceException.class,
                () -> JPASessionUtil.getSession("nonexistent")
        );
        //System.out.print(exception.getMessage());
        assertTrue(exception.getMessage().contains("No Persistence provider for EntityManager named nonexistent"));
    }
}