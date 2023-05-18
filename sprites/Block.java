package sprites;

import general.GameLevel;
import geometryprimitives.Point;
import biuoop.DrawSurface;
import geometryprimitives.Rectangle;
import geometryprimitives.Velocity;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.HitNotifier;
import interfaces.Sprite;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * public class sprites.Block.
 * This class defines a block.
 */

public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rec;
    private Color color;
    private List<HitListener> hitListeners;


    /**
     * constructor from a geometryprimitives.Rectangle.
     * @param rec rectangle
     * @param c   color
     */
    public Block(Rectangle rec, Color c) {
        this.rec = rec;
        this.color = c;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * this method return the "collision shape" of the object.
     * @return this.rec a rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * * This method returns a new velocity according to a specific hit.
     * @param collisionPoint  the point of the collision
     * @param currentVelocity the current velocity
     * @param hitter the ball that hit
     * @return v is the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity v;
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        if (this.rec.edgesList().get(0).isContaining(collisionPoint)
                || this.rec.edgesList().get(3).isContaining(collisionPoint)) {
            //the point is on one of the horizontal bounds
            dy = dy * (-1);
        }
        if (this.rec.edgesList().get(1).isContaining(collisionPoint)
                || this.rec.edgesList().get(2).isContaining(collisionPoint)) {
            //the point is on one of the vertical bounds
            dx = dx * (-1);
        }
        //if the point is on the corner we updated both directions.
        v = new Velocity(dx, dy);
        // or if there isn't any collision none of them.
        this.notifyHit(hitter);
        return v;
    }

    /**
     * this method draws the block on a given DrawSurface.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        //draw the block
        d.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
        //color the edges black
        d.setColor(Color.BLACK);
        for (int i = 0; i < 4; i++) {
            double x1 = this.rec.edgesList().get(i).start().getX();
            double y1 = this.rec.edgesList().get(i).start().getY();
            double x2 = this.rec.edgesList().get(i).end().getX();
            double y2 = this.rec.edgesList().get(i).end().getY();
            d.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
        }
    }

    /**
     * this method knows that time has passed.
     */
    public void timePassed() {
    }

    /**
     * This method adds the block to the game.
     * @param gameLevel the game to add the block to
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * this method removes a block from the game.
     * @param gameLevel the game to remove from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * this method  notify all of the registered interfaces.HitListener objects by calling their hitEvent method.
     * @param hitter the ball that hit something.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * this method add hl as a listener to hit events.
     * @param hl interfaces.HitListener to add
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * this method remove hl from the list of listeners to hit events.
     * @param hl interfaces.HitListener to remove
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
