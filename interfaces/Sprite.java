package interfaces;
import biuoop.DrawSurface;
/**
 * interfaces.Sprite interface.
 */
public interface Sprite {
    /**
     * this method draw the sprite to the screen.
     * @param d the surface to draw on
     */
    void drawOn(DrawSurface d);
    /**
     * this method notify the sprite that time has passed.
     */
    void timePassed();
}
