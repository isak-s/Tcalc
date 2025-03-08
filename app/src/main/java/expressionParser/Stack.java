package expressionParser;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<E> implements Iterable<E>{

    private int size;

    protected Node<E> first;

    public Stack() {
        first = null;
    }

    public void push(E e) {
        Node<E> newFirst = new Node<>(e);

        newFirst.next = first;
        first = newFirst;
        size++;
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
        size--;
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

    public int getSize() {
        return size;
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

    @Override
    public Iterator<E> iterator() {
        return new ExpressionStackIterator();
    }

    private class ExpressionStackIterator implements Iterator<E> {

        @Override
        public boolean hasNext() {
            return first != null;
        }

        @Override
        public E next() {
            return pop();
        }

    }
}
