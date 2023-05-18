package general;

import geometryprimitives.Line;
import geometryprimitives.Point;
import interfaces.Collidable;

import java.util.ArrayList;
import java.util.List;

/**
 * public class general.GameEnvironment.
 * the class holds a list of collidable objects in the game.
 */
public class GameEnvironment {
    private List<Collidable> cList;

    /**
     * Constructor general.GameEnvironment the clist is a list of collidable objects.
     */
    public GameEnvironment() {
        this.cList = new ArrayList<Collidable>();
    }

    /**
     * This method gets a interfaces.Collidable object and adds it to the environment.
     *
     * @param c is the interfaces.Collidable
     */
    public void addCollidable(Collidable c) {
        this.cList.add(c);
    }
    /**
     * this method removes the collidable object from the list.
     * @param c the object to remove
     */
    public void removeCollidable(Collidable c) {
        this.cList.remove(c);
    }
    /**
     * This method finds the closest collision point on a line from a list of collidables.
     *
     * @param trajectory is the line.
     * @return the closest collision point.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Collidable c = null;
        Point minPoint = null;
        if (cList == null) {
            return null;
        }
        for (int i = 0; i < cList.size(); i++) {
            Point p = trajectory.closestIntersectionToStartOfLine(cList.get(i).getCollisionRectangle());
            if (p != null) {
                minPoint = trajectory.closestIntersectionToStartOfLine(cList.get(i).getCollisionRectangle());
                c = cList.get(i);
                break;
            }
        }
        if (minPoint == null) {
            return null;
        }

        for (int i = 0; i < cList.size(); i++) {
            Point p = trajectory.closestIntersectionToStartOfLine(cList.get(i).getCollisionRectangle());
            if (p != null) {
                if (minPoint.distance(trajectory.start()) > p.distance(trajectory.start())) {
                    //if there is a closer point take her instead
                    minPoint = p;
                    c = cList.get(i);
                }
            }
        }
        return new CollisionInfo(minPoint, c);
    }
}