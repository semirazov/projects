package com.seven.uf.alorithms;


import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UFComponent {
    private HashSet<Integer> componentSet = new HashSet<>();
    private List<Pair<Integer, Integer>> unionList = new ArrayList<>();

    public UFComponent(int p) {
        componentSet.add(p);
    }

    private void add(int p) {
        componentSet.add(p);
    }

    public boolean contains(int p){
        return componentSet.contains(p);
    }

    public void union(UFComponent component){
        componentSet.addAll(component.getComponentSet());
        unionList.addAll(component.getUnionList());
    }

    public void createPair(int p, int q) {
        unionList.add(new Pair<>(p, q));
    }

    public HashSet<Integer> getComponentSet() {
        return componentSet;
    }

    public List<Pair<Integer, Integer>> getUnionList() {
        return unionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UFComponent)) return false;
        UFComponent component = (UFComponent) o;
        return getComponentSet().equals(component.getComponentSet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getComponentSet());
    }

    @Override
    public String toString() {
        String set = componentSet.parallelStream()
                .map(Objects::toString)
                .collect(Collectors.joining(", "));

        return String.format("[%s]", set);
    }
}
