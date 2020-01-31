package com.seven.uf.alorithms;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class BaseArrayUF implements UF {

    protected int[] nodes;
    protected int N;

    public BaseArrayUF(int N) {
        this.N = N;
        nodes = IntStream.range(0, N).toArray();
    }

    @Override
    public abstract void union(int p, int q);

    @Override
    public abstract boolean connected(int p, int q);
}
