package com.seventimes.map;

import edu.princeton.cs.algs4.Point2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapAll {
    private double minX = Integer.MAX_VALUE;
    private double maxX = Integer.MIN_VALUE;
    private double minY = Integer.MAX_VALUE;
    private double maxY = Integer.MIN_VALUE;
    private List<Point2D> points = new ArrayList<>();

    public MapAll(String fileName) {
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(line -> {
                try {
                    String[] parts = line.split("\t");
                    double x = Double.parseDouble(parts[5]);
                    double y = Double.parseDouble(parts[4]);

                    minX = Math.min(minX, x);
                    maxX = Math.max(maxX, x);
                    minY = Math.min(minY, y);
                    maxY = Math.max(maxY, y);

                    points.add(new Point2D(x, y));
                }catch (NumberFormatException e){
                    System.out.println("fail");
                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("fail");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Point2D> points() {
        return points;
    }

    public double minX() {
        return minX;
    }

    public double maxX() {
        return maxX;
    }

    public double minY() {
        return minY;
    }

    public double maxY() {
        return maxY;
    }

    public List<Point2D> normalize() {
        final double deltaX = maxX - minX;
        final double deltaY = maxY - minY;

        return points.stream()
                .map(p -> new Point2D((p.x() - minX) / deltaX, (p.y() - minY) / deltaY))
                .collect(Collectors.toList());
    }

    public void saveToFile(List<Point2D> points, String fileName) {
        File citiesFiles = new File(fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(citiesFiles);
            for (Point2D p : points) {
                fos.write(new String(p.x() + " " + p.y() + System.lineSeparator()).getBytes());
            }
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        MapAll map = new MapAll("all.txt");


        map.saveToFile(map.normalize(), "all_normalized.txt");


//        RectHV rect = new RectHV(0.0, 0.0, 1.0, 1.0);
//        StdDraw.setCanvasSize((int)deltaX*80, (int)deltaY*110);
//        StdDraw.enableDoubleBuffering();
//        PointSet kdtree = new KdTree();
//
//        mapPoints.forEach(p -> kdtree.insert(p));
//
//        StdDraw.clear();
//        kdtree.draw();
//        StdDraw.show();
    }
}
