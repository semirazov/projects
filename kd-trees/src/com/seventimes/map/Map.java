package com.seventimes.map;

import edu.princeton.cs.algs4.Point2D;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        File file = new File(new Map().getClass().getClassLoader().getResource("cities.xml").getFile());

        System.out.println(file.exists());

        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        Document doc = dBuilder.parse(file);

        NodeList list = doc.getElementsByTagName("city");

        List<Point2D> points = new ArrayList<>();


        double minX = Integer.MAX_VALUE;
        double maxX = Integer.MIN_VALUE;
        double minY = Integer.MAX_VALUE;
        double maxY = Integer.MIN_VALUE;

        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            Element element = (Element) node;
            double x = Double.parseDouble(element.getAttribute("lon"));
            double y = Double.parseDouble(element.getAttribute("lat"));

            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);

            points.add(new Point2D(x, y));
        }

        double deltaX = maxX - minX + 1;
        double deltaY = maxY - minY + 1;

        List<Point2D> mapPoints = new ArrayList<>();
        for (Point2D p : points) {
            double x = (p.x() - minX + 0.5) / deltaX;
            double y = (p.y() - minY + 0.5) / deltaY;

            mapPoints.add(new Point2D(x, y));
        }

        File citiesFiles = new File("cities.txt");
        FileOutputStream fos = new FileOutputStream(citiesFiles);

        for (Point2D p : mapPoints) {
            fos.write(new String(p.x() + " " + p.y() + System.lineSeparator()).getBytes());
        }
        fos.flush();
        fos.close();

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
