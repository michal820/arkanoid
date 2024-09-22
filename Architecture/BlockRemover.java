package Architecture;
import Game.GameLevel;
import Primitive.Counter;
import Sprites.Ball;
import Sprites.Block;

/**
 * Is responsible for removing blocks when they are hit with a ball.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructs a block remover given a game and the amount of blocks left in the game.
     * @param game the game the block  remover should reference
     * @param removedBlocks the amount of blocks left in the game
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        remainingBlocks = removedBlocks;
    }

    /**
     * This function is called when there is a block that gets hit. It removes the specified block from the game and
     * decreases the counter.
     * @param beingHit the block which  is being hit
     * @param hitter the ball which is hitting it
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        game.removeCollidable(beingHit);
        game.removeSprite(beingHit);
        remainingBlocks.decrease(1);
        game.setBlockCounter(remainingBlocks);
    }
}
