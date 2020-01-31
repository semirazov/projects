package com.seven.uf.alorithms;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QuickUnionBalancedUF extends BaseArrayUF {
    private int[] sz;

    public QuickUnionBalancedUF(int N) {
        super(N);
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
    }

    @Override
    public void union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);
        if (pRoot == qRoot)
            return;

        if (sz[pRoot] < sz[qRoot]) {
            nodes[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            nodes[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    private int root(int p) {
        while (p != nodes[p]) {
            nodes[p] = nodes[nodes[p]];
            p = nodes[p];
        }
        return p;
    }

    @Override
    public String toString() {
        int maxLevel = 0;
        for (int i = 0; i < nodes.length; i++) {
            int nodeLevel = level(nodes[i]);
            if (maxLevel < nodeLevel) {
                maxLevel = nodeLevel;
            }
        }

        return String.valueOf(maxLevel);
    }

    private int level(int p) {
        int level = 0;
        while (nodes[p] != p) {
            p = nodes[p];
            level++;
        }
        return level;
    }
}
