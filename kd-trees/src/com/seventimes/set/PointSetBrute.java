package com.seventimes.set;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class PointSetBrute implements PointSet {

    private TreeSet<Point2D> tree;

    public PointSetBrute() {
        tree = new TreeSet<>();
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public void insert(Point2D p) {
        tree.add(p);
    }

    @Override
    public boolean contains(Point2D p) {
        return tree.contains(p);
    }

    @Override
    public void draw() {
        tree.forEach(Point2D::draw);
    }

    @Override
    public Iterable<Point2D> range(RectHV rect) {
        return tree.stream().filter(rect::contains).collect(Collectors.toList());
    }

    @Override
    public Point2D nearest(Point2D p) {
        return tree.stream().min(Comparator.comparingDouble(p::distanceTo)).get();
    }
}
