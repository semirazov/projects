package com.seven.uf.alorithms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickFindUFTest {

    @Test
    public void testInit(){
        UF uf = new QuickFindUF(4);

        assertEquals("[[0], [1], [2], [3]]", uf.toString());
    }

    @Test
    public void testUnion1(){
        UF uf = new QuickFindUF(4);

        uf.union(0, 1);

        assertEquals("[[0, 1], [2], [3]]", uf.toString());
    }

}