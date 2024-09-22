package Architecture;
import Game.GameLevel;
import Primitive.Counter;
import Sprites.Ball;
import Sprites.Block;
/**
 * The ScoreTrackingListener class is responsible for tracking the score in the game and updating it
 * based on hit events between a block and a ball.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    private GameLevel game;
    /**
     * Constructs a ScoreTrackingListener with the given score counter.
     * @param scoreCounter the Counter object to track the score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * Sets the Game object associated with this listener.
     * @param g the Game object to set
     */
    public void setGame(GameLevel g) {
        game = g;
    }
    /**
     * Updates the score based on hit events between a block and a ball.
     * Increases the score by 5 for each hit, and adds an additional 100 points if
     * the total number of blocks reaches 100.
     * @param beingHit the Block being hit
     * @param hitter the Ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
        if (game.getBlockCounter().getValue()  == 100) {
            currentScore.increase(100);
        }
        game.setScore(currentScore);
    }
}
