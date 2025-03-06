package expressionParser;

import java.util.NoSuchElementException;

public class Stack<E> {

    public Node<E> first;

    public Stack() {
        first = null;
    }

    public void push(E e) {
        Node<E> newFirst = new Node<>(e);

        newFirst.next = first;
        first = newFirst;
    }

    public E pop() throws NoSuchElementException {
        if (first == null) {
            throw new NoSuchElementException("Stack is empty");
        }
        if (first.next == null) {
            E data = first.data;
            first = null;
            return data;
        }
        E data = first.data;
        first = first.next;
        return data;
    }

    public E peek() {
        return first.data;
    }

    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> node = first;
        while (node != null) {

            sb.append(node.data);

            node = node.next;
        }
        return sb.toString();
    }

    public void instertAfter(E thing, E existingThing) {

        Node<E> node = first;
        while (!node.data.equals(existingThing)) {
            node = node.next;
        }
        Node<E> newNode = new Node<E>(thing);
        newNode.next = node.next;
        node.next = newNode;
    }


    static public class Node<E> {
        Node<E> next;
        E data;

        Node(E e) {
            next = null;
            data = e;
        }

        public boolean hasNext() {
            return next != null;
        }
    }
}
