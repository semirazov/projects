package com.seven.uf.alorithms;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RoughUF implements UF {
    private int nodeNumber;
    private List<UFComponent> components = new ArrayList<>();

    public RoughUF(int nodeNumber) {
        this.nodeNumber = nodeNumber;
        initComponents();
    }

    public void union(int p, int q) {
        UFComponent componentP = findComponent(p);
        UFComponent componentQ = findComponent(q);

        componentP.union(componentQ);
        componentP.createPair(p, q);

        components.remove(componentQ);
    }

    public boolean connected(int p, int q) {
        UFComponent componentP = findComponent(p);

        return componentP.contains(q);
    }


    @Override
    public String toString() {
        return getComponents().toString();
    }

    private List<Pair<Integer, Integer>> getUnionList() {
        return components.parallelStream()
                .map(component -> component.getUnionList())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<UFComponent> getComponents() {
        return components;
    }

    private void initComponents() {
        IntStream.range(0, nodeNumber)
                .forEach(this::createComponent);
    }


    private UFComponent findComponent(int p) {
        return components.parallelStream()
                .filter(component -> component.contains(p))
                .findFirst().get();
    }

    private void createComponent(int p) {
        components.add(new UFComponent(p));
    }
}
