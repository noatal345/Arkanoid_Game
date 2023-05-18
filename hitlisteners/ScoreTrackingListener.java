
package hitlisteners;

import general.Counter;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * The Class Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor from a new Score tracking listener.
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * this method updates this counter when ever blocks are being hit and removed.
     * @param beingHit the being hit block
     * @param hitter   the ball that hit
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       this.currentScore.increase(5);
    }
}
