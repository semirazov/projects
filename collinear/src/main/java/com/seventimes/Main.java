package com.seventimes;

public class Main {
    public static void main(String[] args) {
        Point p = new Point(1,1);
        Point q = new Point(2,2);

        LineSegment seg  = new LineSegment(p, q);
        seg.draw();
    }
}
