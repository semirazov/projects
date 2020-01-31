package com.seventimes.stack;

public class LinkedListStack<T> implements Stack<T> {

    private Node head;

    @Override
    public void push(T obj) {
        Node node = new Node();
        node.data = obj;
        node.next = head;

        head = node;
    }

    @Override
    public T pop() {
        T data = head.data;
        head = head.next;
        return data;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        int count = 0;
        Node current = head;

        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    private class Node {
        private T data;
        private Node next;
    }
}
