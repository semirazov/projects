package com.seven.uf.alorithms;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QuickUnionUF extends BaseArrayUF {
    public QuickUnionUF(int N) {
        super(N);
    }

    @Override
    public void union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);
        nodes[pRoot] = qRoot;
    }

    @Override
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    private int root(int p) {
        if (nodes[p] == p) {
            return p;
        }
        return root(nodes[p]);
    }

    @Override
    public String toString() {
        Collection<List<Integer>> trees = IntStream.range(0, N).boxed()
                .collect(Collectors.groupingBy(this::root, Collectors.toList()))
                .values();

        StringBuilder sb = new StringBuilder();
        for (List<Integer> tree : trees) {
            tree.sort(Comparator.comparing(this::level));

            int currentLevel = 0;
            for (Integer node : tree) {
                if(level(node) > currentLevel){
                    currentLevel = level(node);
                    sb.append("\n\r");
                    for (int i = 0; i < currentLevel; i++) {
                        sb.append("\t");
                    }
                    sb.append("|").append("\n\r");
                    for (int i = 0; i < currentLevel; i++) {
                        sb.append("\t");
                    }
                }

                sb.append(node).append(" ");
            }
            sb.append("\n\r");
        }

        return sb.toString().trim();
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
