package EstruturasDeDados;

public class Queue<E> implements Pending<E> {
    private Node<E> base;
    private Node<E> top;
    public void enqueue(E element) {
        Node<E> newNode = new Node<>(element);
        if (top == null) {
            base = newNode;
            top = newNode;
        } else {
            top.next = newNode;
            top = newNode;
        }
    }

    public E dequeue() {
        if (base == null) return null;
        E element = base.data;
        base = base.next;
        if (base == null) top = null;
        return element;
    }
    public boolean empty() {
        return base == null;
    }
    public void add(E element) {
        enqueue(element);
    }
    public E remove() {
        return dequeue();
    }
}
