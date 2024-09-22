package Sprites;

import biuoop.DrawSurface;
import Game.GameLevel;
import Primitive.Point;
import Primitive.Rectangle;
import physics.Collidable;
import physics.Velocity;
import Architecture.HitNotifier;
import Architecture.HitListener;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a block in the game. A block is a collidable object that can be drawn on a `DrawSurface`.
 * It has a rectangular shape defined by its position, width, and height.
 * Blocks can collide with other objects and have a color.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle blockArea;
    private Color color;
    private List<HitListener> hitListeners;
    /**
     * Constructs a new Block with the specified rectangle.
     * @param rect the rectangle defining the block's shape and position
     */
    public Block(Rectangle rect, Color c) {
        blockArea = rect;
        hitListeners = new ArrayList<>();
        color = c;
    }
    /**
     * Constructs a new Block with the specified position, width, height, and color.
     *
     * @param p     the upper-left point of the block's rectangle
     * @param w     the width of the block
     * @param h     the height of the block
     * @param c the color of the block
     */
    public Block(Point p, double w, double h, Color c) {
        blockArea = new Rectangle(p, w, h);
        color = c;
        hitListeners = new ArrayList<>();
    }
    public void changeColor(Color c){
        color = c;
    }
    /**
     * Returns the collision rectangle of the block.
     * @return the collision rectangle of the block
     */
    public  Rectangle getCollisionRectangle() {
        return blockArea;
    }
    /**
     * Adds a HitListener to the list of listeners.
     * @param hl the HitListener to add
     */
    public void addHitListener(HitListener hl)  {
        hitListeners.add(hl);
    }
    /**
     * Removes a HitListener from the list of listeners.
     * @param hl the HitListener to remove
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
    private void  notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Updates the velocity of a ball after a collision with the block.
     * The behavior depends on the edge of the block that was hit.
     * @param collisionPoint    the point of collision between the block and the ball
     * @param currentVelocity   the current velocity of the ball
     * @param hitter the ball itself
     * @return the new velocity of the ball after the collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        String edge  = blockArea.getEdgeFromPoint(collisionPoint);
        Velocity newVelocity  = currentVelocity;
        if (edge == null) {
            return newVelocity;
        }
        if (edge.equals("top") || edge.equals("bottom")) {
            newVelocity = new Velocity(newVelocity.getDx(), -newVelocity.getDy());
        }
        if (edge.equals("right") || edge.equals("left")) {
            newVelocity =  new Velocity(-newVelocity.getDx(), newVelocity.getDy());
        }
        this.notifyHit(hitter);
        return  newVelocity;
    }
    /**
     * Draws the block on the specified `DrawSurface`.
     * @param d the `DrawSurface` on which to draw the block
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        blockArea.drawOn(d, color);
    }
    /**
     * This method is empty as blocks do not change over time.
     */
    public void timePassed() {
    }
    /**
     * Adds the block to the specified `Game` object as both a `Sprite` and a `Collidable`.
     * @param g the `Game` object to which to add the block
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

}
