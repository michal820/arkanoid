package Primitive;

/**
 Represents a point in a 2D coordinate system.
 */
public class Point {
    private double x;
    private double y;
    /**
     Constructs a new geometry.Point object with the given x and y coordinates.
     @param x The x-coordinate of the point.
     @param y The y-coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     Computes the distance between this point and another given point.
     @param other The point to compute the distance to.
     @return The distance between this point and the other point.
     */
    public double distance(Point other) {
        double x1 = x;
        double y1 = y;
        double x2 = other.getX();
        double y2 = other.getY();
        return Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }
    /**
     Compares this point to another point for equality.
     @param other The other point to compare to.
     @return True if the two points have the same x and y coordinates, false otherwise.
     */
    public boolean equals(Point other) {
        return (other.getX() == x && other.getY() == y);
    }
    /**
     Returns the x-coordinate of this point.
     @return The x-coordinate of this point.
     */
    public double getX() {
        return x;
    }
    /**
     Returns the y-coordinate of this point.
     @return The y-coordinate of this point.
     */
    public double getY() {
        return y;
    }
}
