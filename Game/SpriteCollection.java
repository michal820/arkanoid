package Game;

import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
import Sprites.Sprite;

/**
 * The SpriteCollection class represents a collection of sprites in the game.
 * It provides methods for adding sprites, updating their time, and drawing them on a DrawSurface.
 * */
public class SpriteCollection {
    private List<Sprite> sprites;
    /**
     * Constructs a new SpriteCollection with an empty list of sprites.
     */
    public SpriteCollection() {
        sprites =  new ArrayList<>();
    }
    /**
     * Adds a sprite to the collection.
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }
    /**
     * Removes a sprite from the collection.
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * Notifies all sprites in the collection that a unit of time has passed.
     * This method should be called once per game frame to update the sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<Sprite>(sprites);
        for (Sprite i : spritesCopy) {
            i.timePassed();
        }
    }

    /**
     * Draws all sprites in the collection on the given DrawSurface.
     * This method should be called once per game frame to render the sprites.
     * @param d the DrawSurface to draw the sprites on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite i : sprites) {
            i.drawOn(d);
        }
    }
}