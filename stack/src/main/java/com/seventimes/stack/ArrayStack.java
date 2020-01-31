package com.seventimes.stack;

public class ArrayStack<T> implements Stack<T> {

    private int pointer = 0;
    private int capacity = 1;
    private Object[] data;

    public ArrayStack() {
        data = new Object[capacity];
    }

    @Override
    public void push(T obj) {
        if (pointer >= capacity) {
            resize(capacity *= 2);
        }
        data[pointer++] = obj;
    }

    @Override
    public T pop() {
        if (pointer > 0 && pointer <= capacity / 4) {
            resize(capacity /= 2);
        }

        return (T) data[--pointer];
    }

    @Override
    public boolean isEmpty() {
        return pointer == 0;
    }

    @Override
    public int size() {
        return pointer;
    }

    private void resize(int newCapacity) {
        System.out.println("Resize capacity: " + newCapacity);
        Object[] newData = new Object[newCapacity];

        int dataLength = Math.min(newCapacity, data.length);
        System.arraycopy(data, 0, newData, 0, dataLength);

        data = newData;
    }
}
