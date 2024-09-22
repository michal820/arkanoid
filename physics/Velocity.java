package physics;

import Primitive.Point;
/**
 * The Velocity class represents the velocity of an object in 2D space.
 * It stores the horizontal and vertical components of the velocity.
 */
public class Velocity {
    private double dx;
    private double dy;
    /**
     * Constructs a Velocity object with the specified horizontal and vertical components.
     * @param dx the horizontal component of the velocity
     * @param dy the vertical component of the velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * Returns the horizontal component of the velocity.
     * @return the horizontal component of the velocity
     */
    public double getDx() {
        return dx;
    }
    /**
     * Returns the vertical component of the velocity.
     * @return the vertical component of the velocity
     */
    public double getDy() {
        return dy;
    }
    /**
     * Applies the velocity to a given point and returns the new point.
     * The new point is obtained by adding the horizontal component (dx) to the x-coordinate
     * of the given point, and adding the vertical component (dy) to the y-coordinate of the point.
     * @param p the point to which the velocity should be applied
     * @return the new point after applying the velocity
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
    /**
     * Returns the speed of the velocity.
     * The speed is calculated as the magnitude of the velocity vector.
     * @return the speed of the velocity
     */
    public double getSpeed() {
        return Math.sqrt(dx * dx + dy * dy);
    }
    /**
     * Returns the angle of the velocity in degrees.
     * The angle is calculated as the direction of the velocity vector in relation to the positive x-axis.
     * @return the angle of the velocity in degrees
     */
    public double getAngle() {
        return Math.toDegrees(Math.atan2(dy, dx));
    }
    /**
     * Creates a new Velocity object based on a given angle and speed.
     * The velocity components (dx and dy) are calculated using the angle and speed.
     * @param angle the angle of the velocity in degrees
     * @param speed the speed of the velocity
     * @return a new Velocity object with the specified angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleInRadians = Math.toRadians(angle);
        double dx = speed * Math.cos(angleInRadians);
        double dy = speed * Math.sin(angleInRadians);
        return new Velocity(dx, dy);
    }
}