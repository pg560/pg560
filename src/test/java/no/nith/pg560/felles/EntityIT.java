package no.nith.pg560.felles;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;

public class EntityIT {

    private EntityManager entityManager;
    private EntityTransaction tr;

    @Before
    public void setUp() {
        entityManager = createEntityManager("pg560");
        tr = entityManager.getTransaction();
        tr.begin();
    }

    private EntityManager createEntityManager(String persistenceUnit) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit, System.getProperties());
        return emf.createEntityManager();
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @After
    public void cleanUp() {
        entityManager.flush();
        entityManager.clear();
        tr.rollback();
    }
}
