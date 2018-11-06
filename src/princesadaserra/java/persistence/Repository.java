package princesadaserra.java.persistence;

public interface Repository<I, K> {
    I find(K key);
    void update(I item);
    I add(I item);
    void remove(K key);
}
