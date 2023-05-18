
package hitlisteners;

import general.Counter;
import general.GameLevel;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 *this class is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor from a game and a counter.
     * @param gameLevel this game
     * @param blocks num of blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter blocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = blocks;
    }

    /**
     * this method removes blocks that are hit from the game.
     * @param beingHit the block
     * @param hitter the ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }
}