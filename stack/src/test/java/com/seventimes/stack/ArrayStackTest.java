package com.seventimes.stack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayStackTest {

    private Stack<String> stack;

    @Before
    public void setUp(){
        stack = new ArrayStack<>();
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

    @Test
    public void test2(){
        assertTrue(stack.isEmpty());
        assertEquals(stack.size(), 0);

        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("e");
        stack.push("f");
        stack.push("g");
        stack.push("h");
        stack.push("i");
        stack.push("j");
        stack.push("k");
        stack.push("m");
        stack.push("n");
        stack.push("o");
        stack.push("p");


        assertEquals("p", stack.pop());
        assertEquals("o", stack.pop());
        assertEquals("n", stack.pop());
        assertEquals("m", stack.pop());
        assertEquals("k", stack.pop());
        assertEquals("j", stack.pop());
        assertEquals("i", stack.pop());
        assertEquals("h", stack.pop());
        assertEquals("g", stack.pop());
        assertEquals("f", stack.pop());
        assertEquals("e", stack.pop());
        assertEquals("d", stack.pop());
        assertEquals("c", stack.pop());
        assertEquals("b", stack.pop());
        assertEquals("a", stack.pop());
    }
}