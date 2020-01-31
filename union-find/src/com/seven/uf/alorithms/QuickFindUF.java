package com.seven.uf.alorithms;


import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QuickFindUF extends BaseArrayUF {

    public QuickFindUF(int N) {
        super(N);
    }

    @Override
    public void union(int p, int q) {
        int pNode = nodes[p];
        int qNode = nodes[q];
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == qNode) {
                nodes[i] = pNode;
            }
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return nodes[p] == nodes[q];
    }

    @Override
    public String toString() {
        return IntStream.range(0, N).boxed()
                .collect(Collectors.groupingBy(i -> nodes[i], Collectors.toSet()))
                .values().toString();
    }
}
