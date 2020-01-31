package com.seventimes.deque;

import java.util.Iterator;

interface RandomizedQueue<T> extends Iterable<T> {

    boolean isEmpty();                 // is the randomized queue empty?

    int size();                        // return the number of items on the randomized queue

    void enqueue(T item);           // add the item

    T dequeue();                    // remove and return a random item

    T sample();                     // return a random item (but do not remove it);

    Iterator<T> iterator();         // return an independent iterator over items in random order
}