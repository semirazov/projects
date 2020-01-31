package com.seventimes.stack;

public interface Stack<T> {
    void push(T obj);

    T pop();

    boolean isEmpty();

    int size();
}
