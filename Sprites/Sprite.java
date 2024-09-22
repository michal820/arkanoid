package Sprites;

import biuoop.DrawSurface;
/**
 * The Sprite interface represents a game object that can be drawn on a `DrawSurface` and updated over time.
 * Any class implementing this interface must provide methods for drawing the sprite and updating its state.
 */
public interface Sprite {
    /**
     * Draws the sprite on the specified `DrawSurface`.
     * @param d the `DrawSurface` on which to draw the sprite
     */
    void drawOn(DrawSurface d);
    /**
     * Notifies the sprite that a unit of time has passed.
     * This method is called once per game frame and can be used to update the state of the sprite.
     */
    void timePassed();
}
