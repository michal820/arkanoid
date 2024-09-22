package Sprites;
import physics.Velocity;
import Game.GameEnvironment;
import physics.CollisionInfo;
import Game.GameLevel;

import biuoop.DrawSurface;
import Primitive.Line;
import Primitive.Point;

import java.awt.Color;

/**
 This class represents a ball in a 2D space.
 The ball is defined by its center point, radius, color and velocity.
 The ball can be drawn on a DrawSurface and moved in the space.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Construct a new ball given its center point, radius and color.
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Set the velocity of the ball.
     *
     * @param v the velocity of the ball
     */
    public void setVelocity(Velocity v) {
        velocity = v;
    }

    /**
     * Set the velocity of the ball given its change in x and y.
     *
     * @param dx the change in x
     * @param dy the change in y
     */
    public void setVelocity(double dx, double dy) {
        velocity = new Velocity(dx, dy);
    }

    /**
     Sets the game environment for the ball.
     @param e the game environment
     */
    public void setGameEnvironment(GameEnvironment e) {
        gameEnvironment = e;
    }

    /**
     * Get the velocity of the ball.
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * Get the x coordinate of the center point of the ball.
     * @return the x coordinate of the center point of the ball
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Get the y coordinate of the center point of the ball.
     * @return the y coordinate of the center point of the ball
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Get the size (radius) of the ball.
     * @return the size (radius) of the ball
     */
    public int getSize() {
        return radius;
    }

    /**
     * Get the color of the ball.
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * Draws the ball on the given DrawSurface.
     * @param surface the DrawSurface on which to draw the ball
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), radius);
    }

    /**
     * Moves the ball one step according to its current velocity, and reflects its velocity if it hits a
     * collidable object.
     */
    public void moveOneStep() {
        Line trajectory =  new Line(center,
                new Point(center.getX() + velocity.getDx(), center.getY() + velocity.getDy()));
        CollisionInfo info  = gameEnvironment.getClosestCollision(trajectory);
        if (info != null) {
            moveBasedOnInfo(info);
        } else {
            center  = velocity.applyToPoint(center);
        }
    }

    private void moveBasedOnInfo(CollisionInfo info) {
        if (info != null) {
            Point collisionPoint = info.getCollisionPoint();
            velocity = info.getCollisionObject().hit(this, collisionPoint, velocity);
            String edge = info.getCollisionObject().getCollisionRectangle().getEdgeFromPoint(collisionPoint);
            if (edge.equals("top")) {
                center = new Point(collisionPoint.getX(), collisionPoint.getY() - radius);
            } else if (edge.equals("bottom")) {
                center = new Point(collisionPoint.getX(), collisionPoint.getY() + radius);
            } else if (edge.equals("right")) {
                center = new Point(collisionPoint.getX() + radius, collisionPoint.getY());
            } else if (edge.equals("left")) {
                center = new Point(collisionPoint.getX() - radius, collisionPoint.getY());
            }
        }
    }
    /**
     * This method updates the ball's position and velocity for a single time step.
     */
    public void timePassed() {
        moveOneStep();
    }
    /**
     * This method adds the ball to the given Game object as a sprite.
     * @param g The Game object to which to add the ball
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}

