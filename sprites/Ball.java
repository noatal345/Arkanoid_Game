package sprites;

import general.GameLevel;
import general.GameEnvironment;
import geometryprimitives.Line;
import geometryprimitives.Point;
import biuoop.DrawSurface;
import geometryprimitives.Velocity;
import interfaces.Sprite;


/**
 * public class sprites.Ball.
 * the class sprites.Ball have a size(radius), color and location.
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment g;

    /**
     * constructor from two coordinates, radius and color(for the test).
     * @param x               the x coordinate of the ball's center.
     * @param y               the y coordinate of the ball's center.
     * @param r               the balls radius.
     * @param color           the balls color.
     * @param gameEnvironment the game environment
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = new Point((double) x, (double) y);
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.g = gameEnvironment;
    }

    /**
     * constructor from a center point, radius and color.
     * @param center          the ball center point.
     * @param r               the balls radius.
     * @param color           the balls color.
     * @param gameEnvironment the game environment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.g = gameEnvironment;
    }

    /**
     * this method returns the x coordinate of the ball's center point.
     * @return the x coordinate of the ball's center.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * this method returns the y coordinate of the ball's center point.
     * @return the y coordinate of the ball's center.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * this method returns the ball's radius.
     * @return the ball's radius.
     */
    public int getSize() { //size - the radius
        return this.r;
    };

    /**
     * this method returns the ball's color.
     * @return the ball's color.
     */
    public java.awt.Color getColor() {
     return this.color;
    }
    /**
     * this method draws the ball on given DrawSurface.
     * @param surface the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(this.getX(), this.getY(), this.r);
    }

    /**
     * this method determine the velocity.
     * @param v the velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * this method also determine the balls velocity.
     * @param dx the change in position on the x axis.
     * @param dy the change in position on the y axis.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * this method returns the ball's velocity.
     * @return the ball's velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * this method moves the balls center point according to it's velocity and collisions on the way.
     */
    public void moveOneStep() {
        Point end = this.getVelocity().applyToPoint(this.center); //the ball next point
        Line trajectory = new Line(this.center, end);
        if (this.g.getClosestCollision(trajectory) != null) { //not null this trajectory will hit something.
            Point cPoint = this.g.getClosestCollision(trajectory).collisionPoint(); //ball destination
            Velocity v = this.g.getClosestCollision(trajectory).collisionObject().hit(this, cPoint, this.getVelocity());
            this.setVelocity(v); //hit will determine our new velocity.
            this.center = this.getVelocity().applyToPoint(this.center);
        }
        // no intersections.
        this.center = this.getVelocity().applyToPoint(this.center); //move without any obstacles.
    }

    /**
     * this method returns the game environment.
     * @return the game
     */
    public GameEnvironment game() {
        return this.g;
    }

    /**
     * this method knows that time has passed and it's time to move the ball.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * This method is in charge of adding the ball to the game.
     * @param gameLevel the game to add the ball to
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * this method removes a ball from a given game.
     * @param gameLevel the game to remove from
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}