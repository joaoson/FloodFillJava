package EstruturasDeDados;

public class Stack<E> implements Pending<E> {
    private Node<E> top;

    public void push(E element) {
        Node<E> newNode = new Node<>(element);
        newNode.next = top;
        top = newNode;
    }

    public E pop() {
        if (top == null) return null;
        E element = top.data;
        top = top.next;
        return element;
    }

    public boolean empty() {
        return top == null;
    }
    public void add(E element) {
        push(element);
    }
    public E remove() {
        return pop();
    }
}
