package no.nith.pg560.common;

/**
 * Generisk grensesnitt for dataaksess.
 * 
 * @param <T> Entitetstype i domenemodellen
 */
public interface CommonRepositoryInterface<T> {
    T lagre(T objekt);

    void opprett(T objekt);

    T hent(long id);
}
