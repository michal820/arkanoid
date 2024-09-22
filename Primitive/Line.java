package Primitive;
import java.util.List;
/**
 The geometry.Line class represents a line segment between two points.
 The class provides methods for computing the length, middle point,
 and intersection with another line segment.
 */
public class Line {
    private Point start;
    private Point end;
    private static final double EPSILON = 1e-10;
    /**
     Constructs a geometry.Line object with the given start and end points.
     @param start the start point of the line segment
     @param end the end point of the line segment
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end  = end;
    }
    /**
     Constructs a geometry.Line object with the given start and end coordinates.
     @param x1 the x-coordinate of the start point of the line segment
     @param y1 the y-coordinate of the start point of the line segment
     @param x2 the x-coordinate of the end point of the line segment
     @param y2 the y-coordinate of the end point of the line segment
     */
    public Line(double x1, double y1, double x2, double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
    }
    /**
     Computes the length of the line segment.
     @return the length of the line segment
     */
    public double length() {
        return start.distance(end);
    }
    /**
     Computes the middle point of the line segment.
     @return the middle point of the line segment
     */
    public Point middle() {
       return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }
    /**
     Returns the start point of the line segment.
     @return the start point of the line segment
     */
    public Point start() {
        return start;
    }
    /**
     Returns the end point of the line segment.
     @return the end point of the line segment
     */
    public Point end() {
        return end;
    }
    /**
     Determines if the line segment intersects with another line segment.
     @param other the other line segment to check for intersection with
     @return true if the line segments intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        Point intersectionPoint = computeIntersection(other);
        if (intersectionPoint == null) {
            return false;
        }
        return (intersectionPoint.getX() >= Math.min(start.getX(), end.getX()) - EPSILON
                && intersectionPoint.getX() <= Math.max(start.getX(), end.getX()) + EPSILON
                && intersectionPoint.getY() >= Math.min(start.getY(), end.getY()) - EPSILON
                && intersectionPoint.getY() <= Math.max(start.getY(), end.getY()) + EPSILON
                && intersectionPoint.getX() >= Math.min(other.start.getX(), other.end.getX()) - EPSILON
                && intersectionPoint.getX() <= Math.max(other.start.getX(), other.end.getX()) + EPSILON
                && intersectionPoint.getY() >= Math.min(other.start.getY(), other.end.getY()) - EPSILON
                && intersectionPoint.getY() <= Math.max(other.start.getY(), other.end.getY()) + EPSILON);
    }
    /**
     Computes the intersection point of the line segment with another line segment.
     @param other the other line segment to compute intersection with
     @return the intersection point if the line segments intersect, null otherwise
     */
    public Point intersectionWith(Line other) {
        if  (!isIntersecting(other)) {
            return null;
        }
        return (computeIntersection(other));
    }
    private Point computeIntersection(Line other) {
        double slope1 = (end.getY() - start.getY()) / (end.getX() - start.getX());
        double yIntercept1 = start.getY() - slope1 * start.getX();
        double slope2 = (other.end.getY() - other.start.getY()) / (other.end.getX() - other.start.getX());
        double yIntercept2 = other.start.getY() - slope2 * other.start.getX();
        boolean isVertical1 = Math.abs(end.getX() - start.getX()) < EPSILON;
        boolean isVertical2 = Math.abs(other.end.getX() - other.start.getX()) < EPSILON;
        if ((Math.abs(slope1 - slope2) < EPSILON) && !isVertical1 && !isVertical2) {
            return null;
        }
        double x, y;
        if (isVertical1 && isVertical2) {
            return null;
        } else if (isVertical1) {
            x = start.getX();
            y = slope2 * x + yIntercept2;
        } else if (isVertical2) {
            x = other.start.getX();
            y = slope1 * x + yIntercept1;
        } else {
            if (Math.abs(slope1 - slope2) < EPSILON) {
                return null;
            }

            x = (yIntercept2 - yIntercept1) / (slope1 - slope2);
            y = slope1 * x + yIntercept1;
        }

        return new Point(x, y);
    }
    /**
     * Checks if a given point lies on the line segment defined by the start and end points of the line.
     * @param point the point to check
     * @return true if the point lies on the line segment, false otherwise
     */
    public boolean isPointOnLine(Point point) {
        if (Math.abs(end.getX() - start.getX()) < EPSILON) {
            return Math.abs(point.getX() - start.getX()) < EPSILON
                    && point.getY() >= Math.min(start.getY(), end.getY())
                    && point.getY() <= Math.max(start.getY(), end.getY());
        } else {
            double slope = (end.getY() - start.getY()) / (end.getX() - start.getX());
            double yIntercept = start.getY() - slope * start.getX();
            double calculatedY = slope * point.getX() + yIntercept;
            return Math.abs(calculatedY - point.getY()) < EPSILON;
        }
    }
    /**
     * Compares this line to another object to see if they are equal.
     * Two lines are considered equal if they have the same start and end points,
     * regardless of the order in which they appear.
     * @param other the object to compare to
     * @return true if the object is equal to this line, false otherwise
     */
    public boolean equals(Line other) {
        return (((other.end() == end) && (other.start() == start))
                || ((other.end() == start) && (other.start() == end)));
    }
    /**
     * Finds the closest intersection point between the line and a given rectangle.
     * The method assumes that the line intersects with the rectangle.
     * @param rect the rectangle to find the closest intersection point with
     * @return the closest intersection point to the start of the line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);
        if (intersections.size() != 0) {
            double minDistance = intersections.get(0).distance(start);
            Point closestPoint = intersections.get(0);
            for (Point i : intersections) {
                if (i.distance(start) < minDistance) {
                    minDistance = i.distance(start);
                    closestPoint = i;
                }
            }
            return closestPoint;
        }
        return null;
    }
}
