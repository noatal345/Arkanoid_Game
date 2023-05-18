package interfaces;

import geometryprimitives.Velocity;
import sprites.Block;

import java.util.List;

/**
 * The interface holds the Level information.
 */
public interface LevelInformation {
    /**
     * this method returns number of balls of a specific level.
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * this method returns a list with the initial velocity of each ball.
     * @return list the velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * this method returns the paddle's speed.
     * @return paddle's speed.
     */
    int paddleSpeed();

    /**
     * this method returns the paddle's width.
     * @return paddle's width.
     */
    int paddleWidth();

    /**
     * this method returns the paddle's height.
     * @return paddle's width.
     */
    int paddleHeight();

    /**
     * Level name string will be displayed at the top of the screen.
     * @return the string
     */
    String levelName();

    /**
     * this method Returns a background of the level.
     * @return the background
     */
    Sprite getBackground();

    /**
     * this method return a list of the level's blocks.
     * each block contains its size, color and location.
     * @return a list of the level's blocks.
     */
    List<Block> blocks();

    /**
     * this method returns the number of blocks that should be removed before the level is considered to be "cleared".
     * the number of blocks left on the screen.
     * @return number of blocks left on the screen.
     */
    int numberOfBlocksToRemove();
}