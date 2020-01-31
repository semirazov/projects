package com.seven.uf.alorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickUnionUFTest {

    @Test
    public void testConnected() {
        UF uf = new QuickUnionUF(10);

        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(3, 4);
        uf.union(2, 4);

        assertTrue(uf.connected(0, 1));
        assertTrue(uf.connected(0, 2));
        assertTrue(uf.connected(0, 3));
        assertTrue(uf.connected(0, 4));
        assertFalse(uf.connected(5, 6));
        assertFalse(uf.connected(0, 5));
    }

    @Test
    public void testInit() {
        UF uf = new QuickUnionUF(4);

        assertEquals("[[0], [1], [2], [3]]", uf.toString());
    }

    @Test
    public void testUnion1() {
        UF uf = new QuickUnionUF(4);

        uf.union(0, 1);

        assertEquals("[[0, 1], [2], [3]]", uf.toString());
    }


}