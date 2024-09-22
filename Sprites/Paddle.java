package Sprites;

import biuoop.DrawSurface;
import biuoop.GUI;
import Primitive.Point;
import Primitive.Rectangle;
import Game.GameLevel;
import  physics.Collidable;
import physics.Velocity;

import java.awt.Color;
/**
 * This class represents a paddle in the game. The paddle is a collidable object that can be drawn on a `DrawSurface`.
 * It can move horizontally based on user input and can collide with other objects.
 */
public class Paddle implements Sprite, Collidable {
    private Primitive.Rectangle paddleArea;
    private biuoop.KeyboardSensor keyboard;
    private int sensitivity;
    private final Color color = Color.red;
    private  int lBorder;
    private  int rBorder;
    /**
     * Constructs a new paddle with the specified rectangle and GUI.
     *
     * @param rect the rectangle defining the paddle's shape and position
     * @param gui  the GUI object associated with the game
     */
    public Paddle(Primitive.Rectangle rect, GUI gui, int speed) {
        paddleArea = rect;
        keyboard = gui.getKeyboardSensor();
        sensitivity = speed;
    }
    /**
     * Moves the paddle to the left by a fixed sensitivity value.
     */
    public void moveLeft() {
        Primitive.Point newUpperLeft = new Primitive.Point(paddleArea.getUpperLeft().getX() - sensitivity,
                paddleArea.getUpperLeft().getY());
        paddleArea = new Primitive.Rectangle(newUpperLeft,  paddleArea.getWidth(), paddleArea.getHeight());
        if (paddleArea.getUpperLeft().getX() < lBorder) {
            newUpperLeft = new Point(lBorder, paddleArea.getUpperLeft().getY());
            paddleArea = new Rectangle(newUpperLeft, paddleArea.getWidth(), paddleArea.getHeight());
        }
    }
    /**
     * Moves the paddle to the right by a fixed sensitivity value.
     */
    public void moveRight() {
        Primitive.Point newUpperLeft = new Primitive.Point(paddleArea.getUpperLeft().getX() + sensitivity,
                paddleArea.getUpperLeft().getY());
        paddleArea = new Primitive.Rectangle(newUpperLeft,  paddleArea.getWidth(), paddleArea.getHeight());
        if (paddleArea.getUpperLeft().getX() + paddleArea.getWidth() > rBorder) {
            newUpperLeft = new Point(rBorder - paddleArea.getWidth(), paddleArea.getUpperLeft().getY());
            paddleArea = new Rectangle(newUpperLeft, paddleArea.getWidth(), paddleArea.getHeight());
        }
    }
    /**
     * Updates the state of the paddle based on the elapsed time.
     * Moves the paddle left or right based on user input.
     */
    public void timePassed() {
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
           moveLeft();
        }
        if (keyboard.isPressed(keyboard.RIGHT_KEY))  {
            moveRight();
        }
    }
    /**
     * Draws the paddle on the specified `DrawSurface`.
     * @param d the `DrawSurface` on which to draw the paddle
     */
    public void drawOn(DrawSurface d) {
        paddleArea.drawOn(d, color);
    }
    /**
     * Returns the collision rectangle of the paddle.
     * @return the collision rectangle of the paddle
     */
    public Rectangle getCollisionRectangle() {
        return paddleArea;
    }
    /**
     * Updates the velocity of a ball after a collision with the paddle.
     * The behavior depends on the edge of the paddle that was hit.
     *
     * @param collisionPoint    the point of collision between the paddle and the ball
     * @param currentVelocity   the current velocity of the ball
     * @param hitter            the ball itself
     * @return the new velocity of the ball after the collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        String  edge = paddleArea.getEdgeFromPoint(collisionPoint);
        if (edge.equals("top")) {
            double regionSize = paddleArea.getWidth() / 5;
            double ballSpeed = currentVelocity.getSpeed();
            double ballAngle = currentVelocity.getAngle();
            if (collisionPoint.getY() == paddleArea.getUpperLeft().getY()) {
                double relativeCollisionPointX = collisionPoint.getX() - paddleArea.getUpperLeft().getX();

                if (relativeCollisionPointX < regionSize) {
                    ballAngle = 210;
                } else if (relativeCollisionPointX < 2 * regionSize) {
                    ballAngle = 240;
                } else if (relativeCollisionPointX < 3 * regionSize) {
                    ballAngle =  270;
                } else if (relativeCollisionPointX < 4 * regionSize) {
                    ballAngle = 300;
                } else {
                    ballAngle = 330;
                }
            }

            return Velocity.fromAngleAndSpeed(ballAngle, ballSpeed);
        }
        if (edge.equals("left") || edge.equals("right"))  {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        return currentVelocity;
    }
    /**
     * This method adds the ball to the given Game object as a sprite.
     * @param g The Game object to which to add the ball
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
        lBorder = g.getLBorder();
        rBorder = g.getRBorder();
    }
}