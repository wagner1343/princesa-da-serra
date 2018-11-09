package princesadaserra.java.persistence.repository;

import java.util.List;

public interface Repository<I, K> {
    I add(I item);
    I find(K key);
    void update(I item);
    void remove(K key);

    List<I> find(Specification specification);
    List<I> delete(Specification specification);
}
