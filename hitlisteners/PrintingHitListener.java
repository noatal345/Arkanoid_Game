package hitlisteners;

import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * this class.
 */
public class PrintingHitListener implements HitListener {
    /**
     * this method.
     * @param beingHit the block
     * @param hitter the ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A sprites.Block was hit.");
    }
}