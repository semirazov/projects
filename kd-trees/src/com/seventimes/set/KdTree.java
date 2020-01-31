package com.seventimes.set;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.List;

public class KdTree implements PointSet {

    int size;
    private Node root;

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void insert(Point2D point) {
        root = insert(root, null, point, true);
        size++;
    }

    private Node insert(Node node, Node parent, Point2D p, boolean isRed) {
        if (node == null)
            return new Node(parent, p, isRed);

        int compare = compareByOrder(p, node);

        if (compare < 0)
            node.left = insert(node.left, node, p, !node.isRed);
        else
            node.right = insert(node.right, node, p, !node.isRed);

        return node;
    }

    private int compareByOrder(Point2D p, Node node) {
        return node.isRed
                ? Point2D.X_ORDER.compare(p, node.p)
                : Point2D.Y_ORDER.compare(p, node.p);
    }

    @Override
    public boolean contains(Point2D p) {
        return contains(root, p) != null;
    }

    private Node contains(Node node, Point2D p) {
        if (node.p != null) {
            if (Point2D.X_ORDER.compare(p, node.p) < 0) {
                contains(node.left, p);
            } else {
                contains(node.right, p);
            }
        }

        return node;
    }

    @Override
    public void draw() {
        StdDraw.setPenRadius(0.01);
        draw(root, true);
    }

    private void draw(Node node, boolean withLines) {
        if (node != null) {
            Point2D parentPoint = findLowerBounder(node, node.isRed);
            Point2D grandparentPoint = findUpperBounder(node, node.isRed);
            if (withLines) {
                if (node.isRed) {
                    drawVerticalLine(node.p, parentPoint, grandparentPoint);
                } else {
                    drawHorizontalLine(node.p, parentPoint, grandparentPoint);
                }
            }
            drawPoint(node.p);
            draw(node.left, withLines);
            draw(node.right, withLines);
        }
    }

    private void drawPoint(Point2D p) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.0001);
        p.draw();
    }

    private void drawVerticalLine(Point2D p, Point2D lowerBounder, Point2D upperBounder) {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.00005);

        if (lowerBounder.y() > p.y()) {
            StdDraw.line(p.x(), lowerBounder.y(), p.x(), upperBounder.y());
        } else {
            StdDraw.line(p.x(), upperBounder.y(), p.x(), lowerBounder.y());
        }
    }

    private void drawHorizontalLine(Point2D p, Point2D lowerBounder, Point2D upperBounder) {
        StdDraw.setPenColor(StdDraw.BOOK_BLUE);
        StdDraw.setPenRadius(0.00005);
        if (lowerBounder.x() > p.x()) {
            StdDraw.line(upperBounder.x(), p.y(), lowerBounder.x(), p.y());
        } else {
            StdDraw.line(lowerBounder.x(), p.y(), upperBounder.x(), p.y());
        }
    }

    private Point2D findLowerBounder(Node node, boolean isRed) {
        Node currentNode = node;
        Point2D bounder = isRed ? new Point2D(node.p.x(), 0) : new Point2D(0, node.p.y());
        while ((currentNode = currentNode.parent) != null) {
            if (currentNode.isRed == isRed) continue;
            if (isRed) {
                if (currentNode.p.y() > bounder.y() && currentNode.p.y() < node.p.y()) {
                    bounder = currentNode.p;
                }
            } else {
                if (currentNode.p.x() > bounder.x() && currentNode.p.x() < node.p.x()) {
                    bounder = currentNode.p;
                }
            }
        }
        return bounder;
    }

    private Point2D findUpperBounder(Node node, boolean isRed) {
        Node currentNode = node;
        Point2D bounder = isRed ? new Point2D(node.p.x(), 1) : new Point2D(1, node.p.y());
        while ((currentNode = currentNode.parent) != null) {
            if (currentNode.isRed == isRed) continue;
            if (isRed) {
                if (currentNode.p.y() < bounder.y() && currentNode.p.y() > node.p.y()) {
                    bounder = currentNode.p;
                }
            } else {
                if (currentNode.p.x() < bounder.x() && currentNode.p.x() > node.p.x()) {
                    bounder = currentNode.p;
                }
            }
        }
        return bounder;
    }

    @Override
    public Iterable<Point2D> range(RectHV rect) {
        return range(root, rect, new ArrayList<>());
    }

    private List<Point2D> range(Node node, RectHV rect, List<Point2D> points) {
        if (node != null) {
            if (rect.contains(node.p)) {
                points.add(node.p);
            }
            if (node.isRed) {
                if (rect.xmin() < node.p.x()) {
                    range(node.left, rect, points);
                }
                if (rect.xmax() > node.p.x()) {
                    range(node.right, rect, points);
                }
            } else {
                if (rect.ymin() < node.p.y()) {
                    range(node.left, rect, points);
                }
                if (rect.ymax() > node.p.y()) {
                    range(node.right, rect, points);
                }
            }
        }
        return points;
    }

    @Override
    public Point2D nearest(Point2D p) {
        return nearest(root, p, new Point2D(0, 0));
    }

    private Point2D nearest(Node node, Point2D p, Point2D nearest) {
        if (node != null) {
            if (p.distanceTo(node.p) < p.distanceTo(nearest)) {
                nearest = node.p;
            }


        }
        return nearest;
    }

    private static class Node {
        Point2D p;
        Node left;
        Node right;
        Node parent;
        boolean isRed;

        public Node(Point2D point) {
            this.p = point;
        }

        public Node(Node parent, Point2D p, boolean isRed) {
            this.p = p;
            this.isRed = isRed;
            this.parent = parent;
        }
    }
}
