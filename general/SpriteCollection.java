package general;
import java.util.ArrayList;
import java.util.List;
import biuoop.DrawSurface;
import interfaces.Sprite;

/** public class general.SpriteCollection.
 * This class holds a collection of sprites.
 */

public class SpriteCollection {
    private List<Sprite> spriteColl;

    /**
     * Constructor general.SpriteCollection holds the list of sprite objects.
     */
    public SpriteCollection() {
        this.spriteColl = new ArrayList<Sprite>();
    }
    /**
     * This method get a interfaces.Sprite and adds it to the sprite collection.
     * @param s is the interfaces.Sprite
     */
    public void addSprite(Sprite s) {
        this.spriteColl.add(s);
    }

    /**
     * this method get a sprite and removes it from the sprite collection.
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.spriteColl.remove(s);
    }
    /**
     * This method notify the sprites that time has passed, call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < spriteColl.size(); i++) {
            this.spriteColl.get(i).timePassed();
        }
    }
    /**
     * This method call drawOn(d) on all sprites.
     * @param d the surface
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spriteColl.size(); i++) {
            this.spriteColl.get(i).drawOn(d);
        }
    }
}
