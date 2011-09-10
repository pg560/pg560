package no.nith.pg560.common;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CommonRepository<T> {
    @PersistenceContext(unitName = "pg560")
    private EntityManager entityManager;

    private final Class<T> domainType;

    protected CommonRepository(Class<T> domeneType) {
        this.domainType = domeneType;
    }

    protected CommonRepository(Class<T> domeneType, EntityManager em) {
        this(domeneType);
        entityManager = em;
    }

    protected void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
