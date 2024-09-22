package Animation;

import Primitive.Counter;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class EndScreen implements Animation{
    private KeyboardSensor keyboard;
    private Counter score;
    private boolean hasWon;
    private boolean stop;
    public EndScreen(KeyboardSensor k, Counter score, boolean hasWon) {
        this.keyboard = k;
        this.score = score;
        this.hasWon = hasWon;
        this.stop = false;
    }
    public void doOneFrame(DrawSurface d) {
        if(!hasWon) {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + String.valueOf(score.getValue()), 32);
        }
        else {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + String.valueOf(score.getValue()), 32);
        }
    }
    public boolean shouldStop() { return this.stop; }
}
