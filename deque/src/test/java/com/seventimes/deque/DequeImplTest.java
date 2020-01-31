package com.seventimes.deque;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class DequeImplTest {

    private Deque<String> deque;
    private Iterator<String> iterator;

    private Deque<String> build(String... items) {
        Deque<String> deque = new DequeImpl<String>();
        for (int i = 0; i < items.length; i++) {
            deque.addLast(items[i]);
        }
        return deque;
    }

    @Before
    public void setUp() {
        deque = null;
        iterator = null;
    }

    @Test
    public void emptyDequeConstructorTest() {
        deque = new DequeImpl<>();

        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    @Test
    public void isEmptyTest() {
        deque = build();

        assertTrue(deque.isEmpty());

        deque.addFirst("Sasha");
        deque.removeFirst();
        assertTrue(deque.isEmpty());

        deque.addFirst("Sasha");
        deque.removeLast();
        assertTrue(deque.isEmpty());

        deque.addLast("Sasha");
        deque.removeFirst();
        assertTrue(deque.isEmpty());

        deque.addLast("Sasha");
        deque.removeLast();
        assertTrue(deque.isEmpty());

        deque.addFirst("Sasha");
        assertFalse(deque.isEmpty());
        deque.removeFirst();

        deque.addLast("Sasha");
        assertFalse(deque.isEmpty());
        deque.removeLast();
    }

    @Test
    public void sizeTest() {
        deque = build();

        assertEquals(0, deque.size());

        deque.addFirst("Sasha");
        assertEquals(1, deque.size());

        deque.addLast("Tori");
        assertEquals(2, deque.size());

        deque.removeFirst();
        assertEquals(1, deque.size());

        deque.removeLast();
        assertEquals(0, deque.size());
    }

    @Test
    public void addFirstTest() {
        deque = build();

        deque.addFirst("Sasha");
        assertEquals("Sasha", deque.removeFirst());
    }

    @Test
    public void addFirstIncrementSizeTest() {
        deque = build();

        assertEquals(0, deque.size());

        deque.addFirst("Sasha");
        assertEquals(1, deque.size());

        deque.addFirst("Tori");
        assertEquals(2, deque.size());
    }

    @Test
    public void addFirstInANonEmptyQueueTest() {
        deque = build("Sasha", "Lexi");

        deque.addFirst("Tori");
        assertEquals("Tori", deque.removeFirst());
        assertEquals("Sasha", deque.removeFirst());
        assertEquals("Lexi", deque.removeFirst());
    }

    @Test(expected=IllegalArgumentException.class)
    public void addFirstWithNullObjectTest() {
        deque = build();

        deque.addFirst(null);
    }

    @Test
    public void addLastTest() {
        deque = build();

        deque.addLast("Sasha");
        assertEquals(deque.removeFirst(), "Sasha");
    }

    @Test
    public void addLastIncrementSizeTest() {
        deque = build();

        assertEquals(deque.size(), 0);
        deque.addLast("Sasha");
        assertEquals(deque.size(), 1);
        deque.addLast("Tori");
        assertEquals(deque.size(), 2);
    }

    @Test
    public void addLastInANonEmptyQueueTest() {
        deque = build("Sasha", "Lexi");

        deque.addLast("Tori");

        assertEquals(deque.removeFirst(), "Sasha");
        assertEquals(deque.removeFirst(), "Lexi");
        assertEquals(deque.removeFirst(), "Tori");
    }

    @Test(expected=IllegalArgumentException.class)
    public void addLastWithNullObjectTest() {
        deque = build();
        deque.addLast(null);
    }

    @Test
    public void removeFirstTest() {
        deque = build("Sasha", "Tori");

        assertEquals(deque.removeFirst(), "Sasha");
    }

    @Test
    public void removeFirstDecrementSizeTest() {
        deque = build("Sasha", "Tori");

        assertEquals(2, deque.size());

        deque.removeFirst();
        assertEquals(1, deque.size());

        deque.removeFirst();
        assertEquals(0, deque.size());
    }

    @Test(expected=NoSuchElementException.class)
    public void removeFirstFromEmptyQueueTest() {
        deque = build();

        deque.removeFirst();
    }

    @Test
    public void removeLastTest() {
        deque = build("Sasha", "Tori");

        assertEquals(deque.removeLast(), "Tori");
    }

    @Test
    public void removeLastDecrementSizeTest() {
        deque = build("Sasha", "Tori");

        assertEquals(2, deque.size());

        deque.removeLast();
        assertEquals(1, deque.size());

        deque.removeLast();
        assertEquals(0, deque.size());
    }

    @Test(expected=NoSuchElementException.class)
    public void removeLastFromEmptyQueueTest() {
        deque = build();

        deque.removeLast();
    }

    @Test
    public void iteratorTraversesInOrderTest() {
        iterator = build("Sasha", "Tori", "Lexi").iterator();

        assertEquals("Sasha", iterator.next());
        assertEquals("Tori", iterator.next());
        assertEquals("Lexi", iterator.next());
    }

    @Test
    public void iteratorHasNextTest() {
        iterator = build("Sasha", "Tori").iterator();

        assertTrue(iterator.hasNext());

        iterator.next();
        assertTrue(iterator.hasNext());

        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test(expected=NoSuchElementException.class)
    public void iteratorNextThrowsOnEmptyDequeTest() {
        iterator = build().iterator();

        iterator.next();
    }

    @Test(expected=UnsupportedOperationException.class)
    public void iteratorDoesNotSupportRemoveTest() {
        iterator = build().iterator();

        iterator.remove();
    }

}