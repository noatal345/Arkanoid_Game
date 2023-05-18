// ID 209327279

package geometryprimitives;

/** public class GeometryPrimitives.Point.
 * @author Noa Tal
 * the class has coordinates an x and a y value, it can measure the distance to other points,
 * and check if it is equal to another point.
 */

public class Point {
    private double x;
    private double y;

    /**
     * constructor.
     * @param x the x coordinate of the point.
     * @param y the y coordinate of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * this method measures the distance from this point to another point.
     * @param other another point.
     * @return the distance of this point from the other point.
     */
    public double distance(Point other) {
        double dis;
        dis = Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));
        return dis;
    }
    /**
     * this method checks if two points are the same.
     * @param other another point.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if (this.x == other.x && this.y == other.y) {
            return true;
        }
        return false;
    }
    /**
     * this method returns the x value of this point.
     * @return the x value of this point.
     */
    public double getX() {
        return this.x;
    }
    /**
     * this method returns the y value of this point.
     * @return the y value of this point.
     */
    public double getY() {
        return this.y;
    }
}
