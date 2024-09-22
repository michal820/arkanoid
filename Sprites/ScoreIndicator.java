package Sprites;
import biuoop.DrawSurface;
import Game.GameLevel;
import Primitive.Counter;

import java.awt.Color;
/**
 * The ScoreIndicator class represents a sprite that displays the current score on the game screen.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;
    private GameLevel game;
    /**
     * Constructs a new ScoreIndicator with the specified Game and Counter.
     * The ScoreIndicator is added as a sprite to the Game.
     * @param g     the Game to associate the ScoreIndicator with
     * @param score the Counter representing the score
     */
    public ScoreIndicator(GameLevel g, Counter score) {
        game = g;
        scoreCounter = score;
        game.addSprite(this);
    }
    /**
     * Draws the ScoreIndicator on the given DrawSurface.
     * The ScoreIndicator is displayed as a white rectangle with the current score in black text.
     * @param d the DrawSurface on which to draw the ScoreIndicator
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 30);
        d.setColor(Color.black);
        d.drawText(400, 25, "Score: " + String.valueOf(scoreCounter.getValue()), 15);
    }
    /**
     * Updates the score value of the ScoreIndicator.
     * The value is updated to match the current score in the associated Game.
     */
    public void timePassed() {
        scoreCounter.setValue(game.getScore().getValue());
    }
}
