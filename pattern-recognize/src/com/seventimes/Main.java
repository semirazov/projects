package com.seventimes;

import edu.princeton.cs.algs4.StdDraw;

public class Main {
    public static void main(String[] args) {
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 150);
        StdDraw.setYscale(0, 150);

        Point p = new Point(0,0);
        Point q = new Point(15,15);

        Point r = new Point(5,15);
        Point t = new Point(7,24);

        LineSegment seg  = new LineSegment(p, q);
        LineSegment seg1  = new LineSegment(r, t);
        p.draw();
        q.draw();
        r.draw();
        t.draw();
        seg.draw();

        StdDraw.show();

    }
}
