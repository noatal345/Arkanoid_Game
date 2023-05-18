
package interfaces;

import sprites.Ball;
import sprites.Block;

/**
 * this interface is a hit listener.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit the block
     * @param hitter the ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}
