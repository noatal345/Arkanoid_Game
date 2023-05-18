package general;

import geometryprimitives.Point;
import interfaces.Collidable;

/** public class general.CollisionInfo.
 * This class general.CollisionInfo holds information about a collision.
 */
public class CollisionInfo {
    private Point p;
    private Collidable c;

    /**
     * Constructor general.CollisionInfo defined by the collision point and the the object involved.
     * @param p point the point of the collision.
     * @param c the object involved in the collision.
     */
    public CollisionInfo(Point p, Collidable c) {
        this.p = p;
        this.c = c;
    }
    /**
     * This method return the point at which the collision occurs..
     * @return the collision point.
     */
    public Point collisionPoint() {
        return this.p;
    }
    /**
     * This method return the collidable object involved in the collision.
     * @return the collidable object.
     */
    public Collidable collisionObject() {
        return this.c;
    }

}
