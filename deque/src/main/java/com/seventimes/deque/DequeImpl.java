package com.seventimes.deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DequeImpl<T> implements Deque<T> {
    private Node<T> first;
    private Node<T> last;

    public DequeImpl() {

    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        int count = 0;
        Node<T> current = first;
        while (current != null) {
            current = current.next;
            count++;
        }
        return count;
    }

    @Override
    public void addFirst(T data) {
        checkNull(data);

        Node<T> node = new Node<>(data);
        if (first == null) {
            first = node;
            last = node;
        } else {
            node.next = first;
            first = node;
        }
    }

    @Override
    public void addLast(T data) {
        checkNull(data);

        Node<T> node = new Node<>(data);
        if (last == null) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
    }

    @Override
    public T removeFirst() {
        checkRemove();
        T data = first.data;

        first = first.next;
        if (isEmpty()) {
            last = null;
        }

        return data;
    }

    @Override
    public T removeLast() {
        checkRemove();
        T data = last.data;
        if (first == last) {
            first = last = null;
        } else {
            Node<T> prev = first;
            while (prev.next != last) {
                prev = prev.next;
            }
            prev.next = null;
            last = prev;
        }

        return data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator<T> iterator = iterator();

        while (iterator.hasNext()) {
            T data = iterator.next();
            sb.append(data);

            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }

        return sb.append("]").toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl<>(first);
    }

    private void checkNull(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
    }

    private void checkRemove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    private static class IteratorImpl<T> implements Iterator<T> {
        private Node<T> current;

        public IteratorImpl(Node<T> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            checkNull();
            T data = current.data;
            current = current.next;

            return data;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        private void checkNull() {
            if (current == null) {
                throw new NoSuchElementException();
            }
        }
    }
}
