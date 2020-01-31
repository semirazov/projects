package com.seven.uf.structure;

import com.seven.uf.alorithms.RoughUF;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoughUFTest {

    @Test
    public void shouldInit(){
        RoughUF uf = new RoughUF(4);

        assertEquals("[[0], [1], [2], [3]][]", uf.toString());
    }

    @Test
    public void testUnion1(){
        RoughUF uf = new RoughUF(4);

        uf.union(0,1);

        assertEquals("[[0, 1], [2], [3]][0=1]", uf.toString());
    }

    @Test
    public void testUnion2(){
        RoughUF uf = new RoughUF(4);

        uf.union(0,1);
        uf.union(0, 3);

        assertEquals("[[0, 1, 3], [2]][0=1, 0=3]", uf.toString());
    }

    @Test
    public void testUnion3(){
        RoughUF uf = new RoughUF(4);

        uf.union(0,1);
        uf.union(0, 3);
        uf.union(3, 2);

        assertEquals("[[0, 1, 2, 3]][0=1, 0=3, 3=2]", uf.toString());
    }



}