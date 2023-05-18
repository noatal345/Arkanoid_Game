// ID 209327279

package sprites;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import java.awt.Color;

import general.GameLevel;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import geometryprimitives.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;



/**
 * public class sprites.Paddle.
 * This class defines a paddle.
 */

public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rec;
    private Color color;
    private double screenWidth;
    private int speed;

    /**
     * constructor from a geometryprimitives.Rectangle, Color, KeyboardSensor.
     *
     * @param r        rectangle
     * @param c        color
     * @param keyboard KeyboardSensor
     * @param speed speed
     */
    public Paddle(Rectangle r, Color c, KeyboardSensor keyboard, int speed) {
        this.color = c;
        this.rec = r;
        this.screenWidth = 800;
        this.keyboard = keyboard;
        this.speed = speed;
    }

    /**
     * this method moves the paddle to the left according to the keyboard commands.
     */
    public void moveLeft() {
        if (this.rec.getUpperLeft().getX() > 20) { //check screen bound.
            Point newUpperLeft = new Point(this.rec.getUpperLeft().getX() - speed, this.rec.getUpperLeft().getY());
            this.rec = new Rectangle(newUpperLeft, this.rec.getWidth(), this.rec.getHeight());
            // move according to the new point
        }
    }

    /**
     * this method moves the paddle to the right according to the keyboard commands.
     */
    public void moveRight() {
        if (this.rec.getLowerRight().getX() < screenWidth - 20) { //check screen bound.
            Point newUpperLeft = new Point(this.rec.getUpperLeft().getX() + speed, this.rec.getUpperLeft().getY());
            this.rec = new Rectangle(newUpperLeft, this.rec.getWidth(), this.rec.getHeight());
            // move according to the new point
        }
    }

    /**
     * This method checks if the "left" or "right" keys are pressed, and if so moves the accordingly.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * this method draws the paddle on a given DrawSurface.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        int x = (int) this.rec.getUpperLeft().getX();
        int y = (int) this.rec.getUpperLeft().getY();
        d.fillRectangle(x, y, (int) this.rec.getWidth(), (int) this.rec.getHeight());
    }

    /**
     * this method return the rectangle that the block is based on.
     * @return the rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * This method returns the new velocity 1 of 5 options depends on which part the ball hits the paddle on.
     * @param collisionPoint the collision GeometryPrimitives.Point
     * @param currentVelocity the current geometryprimitives.Velocity
     * @param hitter the ball that hit
     * @return the new velocity
     */

    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double paddleRegion = this.rec.getWidth() / 5; // each paddle region.
        if (collisionPoint.getX() <= paddleRegion + this.rec.getUpperLeft().getX()) {
            //area 1 collision at the left-most region
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
        }
        if (collisionPoint.getX() <= paddleRegion * 2 + this.rec.getUpperLeft().getX()) {
            //area 2 collision at the middle-left region
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
        }
        if (collisionPoint.getX() <= paddleRegion * 3 + this.rec.getUpperLeft().getX()) {
            //area 3 - ball hits the middle region.
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            //the balls keeps its horizontal direction and only change its vertical.
        }
        if (collisionPoint.getX() <= paddleRegion * 4 + this.rec.getUpperLeft().getX()) {
            //area 4 collision at the middle-right region
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
        }
        //else area 5 collision the right-most region.
        return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
    }

    /**
     * This method add this paddle to the game.
     * @param g is the game to add to
     */

    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

}