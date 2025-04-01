package EstruturasDeDados;

public interface Pending<E> {
    void add(E element);
    E remove();
    boolean empty();
}

