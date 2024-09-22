package Architecture;

import Game.GameLevel;
import Primitive.Counter;
import Sprites.Ball;
import Sprites.Block;

/**
 * Responsible for removing a ball when it hits the bottom border.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter ballsLeft;

    /**
     * Constructs a BallRemover by specifying a game and the amount of balls left in the game.
     * @param game the game the ball remover should reference.
     * @param ballsLeft the amount of balls left still "alive" in the game.
     */
    public BallRemover(GameLevel game, Counter ballsLeft) {
        this.game = game;
        this.ballsLeft = ballsLeft;
    }

    /**
     * Is called when the ball hits the bottom block. It removes the ball from the game and decreases the counter which
     * specifies the amount of balls left.
     * @param beingHit The block which was  hit
     * @param hitter the Ball which hit it
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        game.removeSprite(hitter);
        ballsLeft.decrease(1);
        game.setBallCounter(ballsLeft);
    }
}
