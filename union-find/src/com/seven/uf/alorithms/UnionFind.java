package com.seven.uf.alorithms;

public interface UnionFind {

    void union(int p, int q);

    boolean connected(int p, int q);
}


public class QuickFind implements UnionFind {

    private int[] nodes;

    public QuickFind(int n) {
        nodes = new int[n];

        for (int i = 0; i < n; i++) {
            nodes[i] = i;
        }
    }

    public void union(int p, int q) { ... }
    public boolean connected(int p, int q) { ... }
}