
package hitlisteners;

import general.Counter;
import general.GameLevel;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * The class sprites.Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * constructor from a game and a counter.
     * @param gameLevel  this game
     * @param balls num of balls
     */
    public BallRemover(GameLevel gameLevel, Counter balls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = balls;
    }

    /**
     * this method removes balls that hits the death-region.
     * @param beingHit the death-region block
     * @param hitter   the ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(1);
    }
}
