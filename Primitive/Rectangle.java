package Primitive;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents a rectangle in a two-dimensional space.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line topEdge;
    private Line bottomEdge;
    private Line rightEdge;
    private Line leftEdge;
    /**
     * Constructs a new rectangle with the specified upper-left point, width, and height.
     * @param upperLeft the upper-left point of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        topEdge = new Line(upperLeft, new Point(upperLeft.getX() + width, upperLeft.getY()));
        bottomEdge = new Line(upperLeft.getX(), upperLeft.getY() + height,
                upperLeft.getX() + width, upperLeft.getY() + height);
        leftEdge = new Line(upperLeft, new Point(upperLeft.getX(), upperLeft.getY() + height));
        rightEdge = new Line(upperLeft.getX() + width, upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY() + height);
    }
    /**
     * Returns the edge of the rectangle that contains the given collision point.
     * @param collisionPoint the collision point
     * @return the edge of the rectangle containing the collision point
     */
    public String getEdgeFromPoint(Point collisionPoint) {
        String edge = "";
        if (topEdge.isPointOnLine(collisionPoint)) {
            edge = "top";
        }
        if (bottomEdge.isPointOnLine(collisionPoint)) {
            edge = "bottom";
        }
        if (rightEdge.isPointOnLine(collisionPoint)) {
            edge = "right";
        }
        if (leftEdge.isPointOnLine(collisionPoint)) {
           edge = "left";
        }
        return edge;
    }
    /**
     * Finds the intersection points between the rectangle and a given line.
     * @param line the line to find intersection points with
     * @return a list of intersection points between the rectangle and the line
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<>();
        if (topEdge.intersectionWith(line)  != null) {
            intersections.add(topEdge.intersectionWith(line));
        }
        if (bottomEdge.intersectionWith(line)  != null) {
            intersections.add(bottomEdge.intersectionWith(line));
        }
        if  (rightEdge.intersectionWith(line)  != null) {
            intersections.add(rightEdge.intersectionWith(line));
        }
        if (leftEdge.intersectionWith(line)  != null) {
            intersections.add(leftEdge.intersectionWith(line));
        }
        return intersections;
    }

    /**
     * Checks if the rectangle is equal to another rectangle.
     * @param other the other rectangle to compare to
     * @return true if the rectangles are equal, false otherwise
     */
    public boolean equals(Rectangle other) {
        return upperLeft.equals(other.upperLeft) && width == other.getWidth() && height == other.getHeight();
    }
    /**
     * Returns the width of the rectangle.
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }
    /**
     * Returns the height of the rectangle.
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }
    /**
     * Returns the upper left  point of the rectangle.
     * @return the upper left point
     */
    public Point getUpperLeft() {
        return upperLeft;
    }
    /**
     * Draws the rectangle on a given DrawSurface with the specified color.
     * @param d the DrawSurface to draw on
     * @param c the color of the rectangle
     */
    public void drawOn(DrawSurface d, Color c) {
        d.setColor(c);
        d.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width,  (int) height);
        d.setColor(Color.black);
        d.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width,  (int) height);
    }
}
