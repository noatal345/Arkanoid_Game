
package geometryprimitives;

/** public class geometryprimitives.Velocity.
 * @author Noa Tal
 * This class geometryprimitives.Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;
    /**
     * constructor velocity specified the distances from the coordinate axis.
     * @param dx the change in position on the x axis.
     * @param dy the change in position on the y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * construct Velocity object taking an angle and a speed creating a new instance instead of the constructor.
     * @param angle the angle , direction to move.
     * @param speed the speed is the number of units to move.
     * @return geometryprimitives.Velocity object.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) { //convert to radians
        double dx = Math.cos(Math.toRadians(angle - 90)) * speed; //assuming up is angle 0
        double dy = Math.sin(Math.toRadians(angle - 90)) * speed;
        return new Velocity(dx, dy);
    }
    /**
     * this method creates a new point position.
     * @param p a point with the stating coordinates (x,y).
     * @return newPoint with the new coordinates (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        double x = p.getX() + this.dx;
        double y = p.getY() + this.dy;
        Point newPoint = new Point(x, y);
        return newPoint;
    }

    /**
     * this method returns the velocity's change in position on the x axis.
     * @return the velocity's change in position on the x axis.
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * this method returns the velocity's change in position on the y axis.
     * @return the velocity's change in position on the y axis.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * This method calculates the speed based on a current velocity.
     * @return speed the speed of the object.
     */
    public double getSpeed() {
        double speed = Math.sqrt(this.dx * this.dx + this.dy * this.dy);
        return speed;
    }
}