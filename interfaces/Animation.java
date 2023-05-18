
package interfaces;

import biuoop.DrawSurface;

/**
 * this is the Animation interface.
 */
public interface Animation {
    /**
     * this methods daws the animation on the screen.
     * @param d a draw surface to draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * this method tells if the animation drawing should stop.
     * @return true if the animation should stop.
     */
    boolean shouldStop();
}