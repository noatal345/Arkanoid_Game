
package interfaces;

import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import geometryprimitives.Velocity;
import sprites.Ball;

/**
 * interfaces.Collidable interface.
 * @author Noa Tal
 */
public interface Collidable {
    /**
     * this method return the "collision shape" of the object.
     * @return rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * This method generates a new velocity according to the hit.
     * @param collisionPoint the collision point
     * @param currentVelocity the current velocity
     * @param hitter the ball that hit
     * @return new velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
