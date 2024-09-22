package Architecture;
import Sprites.Block;
import Sprites.Ball;

/**
 * A listener that listens for a collision between a ball and a block.
 */
public interface HitListener {
    /**
     * The event that the listener executes upon detecting that a block hit a ball.
     * @param beingHit the block being hit
     * @param hitter the ball hitting it
     */
    void hitEvent(Block beingHit, Ball hitter);
}
