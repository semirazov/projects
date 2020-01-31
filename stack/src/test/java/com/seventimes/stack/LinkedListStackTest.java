package com.seventimes.stack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinkedListStackTest {

    private Stack<String> stack;

    @Before
    public void setUp(){
        stack = new LinkedListStack<>();
    }

    @Test
    public void test1(){
        assertTrue(stack.isEmpty());
        assertEquals(stack.size(), 0);

        stack.push("a");
        stack.push("b");
        stack.push("c");

        assertFalse(stack.isEmpty());
        assertEquals(stack.size(), 3);

        assertEquals("c", stack.pop());
        assertEquals("b", stack.pop());

        assertEquals(stack.size(), 1);

        stack.push("d");

        assertEquals("d", stack.pop());
        assertEquals("a", stack.pop());

        assertTrue(stack.isEmpty());
    }


}