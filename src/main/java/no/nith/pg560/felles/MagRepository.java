package no.nith.pg560.felles;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Generisk implementasjon av vanlige dataaksessoperasjoner med JPA.
 * 
 * @param <T> Entitetstype i domenemodellen
 */
public class MagRepository<T> {
    private static final String UGYLDIG_NOEKKELVERDI = "Ugyldig nøkkelverdi";
    private static final String PARAMETER_IKKE_SATT = "Parameter ikke satt";

    @PersistenceContext(unitName = "pg560")
    private EntityManager entityManager;

    private final Class<T> domeneType;

    protected MagRepository(Class<T> domeneType) {
        this.domeneType = domeneType;
    }

    protected MagRepository(Class<T> domeneType, EntityManager em) {
        this(domeneType);
        entityManager = em;
    }

    protected void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void opprett(T objekt) {
        if (objekt == null) {
            throw new IllegalArgumentException(PARAMETER_IKKE_SATT);
        }
        entityManager.persist(objekt);
    }

    public T hent(long id) {
        if (id < 0) {
            throw new IllegalArgumentException(UGYLDIG_NOEKKELVERDI);
        }
        return entityManager.find(domeneType, id);
    }

    public T lagre(T objekt) {
        if (objekt == null) {
            throw new IllegalArgumentException(PARAMETER_IKKE_SATT);
        }
        return entityManager.merge(objekt);
    }
}
