package com.seventimes.deque;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueueImpl<T> implements RandomizedQueue<T> {

    private Node<T> head;
    private int size;

    public RandomizedQueueImpl() {
    }

    public RandomizedQueueImpl(RandomizedQueue<T> queue) {
        for (int i = 0; i < queue.size(); i++) {
            enqueue(queue.dequeue());
        }
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void enqueue(T item) {
        incrementSize();

        Node<T> node = new Node<>();
        node.data = item;
        node.next = head;

        head = node;
        size++;
    }

    @Override
    public T dequeue() {
        checkRemove();
        decrementSize();

        int removeIndex = new Random().nextInt(size);

        if (removeIndex == 0) {
            T data = head.data;
            head = head.next;

            return data;
        } else {
            Node<T> prevItem = getItemByIndex(size - 1);
            T data = prevItem.next.data;
            prevItem.next = prevItem.next.next;

            return data;
        }
    }

    @Override
    public T sample() {
        checkRemove();

        int sampleIndex = new Random().nextInt(size);

        if (sampleIndex == 0) {
            return head.data;
        } else {
            Node<T> prevItem = getItemByIndex(size - 1);
            return prevItem.next.data;
        }
    }

    @Override
    public Iterator<T> iterator() {
        RandomizedQueue<T> queue = new RandomizedQueueImpl<>(this);
        return null;
    }

    private void copy() {
        RandomizedQueue<T> queueCopy = new RandomizedQueueImpl<>();
        while (size() != 0) {
            T data = dequeue();
            queueCopy.enqueue(data);
        }
    }

    private void incrementSize() {
        size++;
    }

    private void decrementSize() {
        size--;
    }

    private void checkRemove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    private Node<T> getItemByIndex(int index) {
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;
    }

    private static class RandomizedQueueIterator<T> implements Iterator<T> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
