package no.nith.pg560.felles;

/**
 * Generisk grensesnitt for dataaksess.
 * 
 * @param <T> Entitetstype i domenemodellen
 */
public interface MagRepositoryInterface<T> {
    T lagre(T objekt);

    void opprett(T objekt);

    T hent(long id);
}
