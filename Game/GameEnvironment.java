package Game;

import Primitive.Line;
import Primitive.Point;
import Primitive.Rectangle;
import physics.Collidable;
import physics.CollisionInfo;

import java.util.ArrayList;
import java.util.List;
/**
 * The GameEnvironment class represents the environment of the game, which contains collidable objects.
 * It provides methods for adding and removing collidables,
 * as well as finding the closest collision to a given trajectory.
 */
public class GameEnvironment {
    private List<Collidable> collidables;
    /**
     * Constructs a new GameEnvironment with an empty list of collidables.
     */
    public GameEnvironment() {
        collidables = new ArrayList<>();
    }
    /**
     * Adds a collidable object to the game environment.
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        collidables.add((c));
    }

    /**
     * Removes a collidable object from the  game environment.
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * Finds the closest collision point and collidable object on a given trajectory.
     * @param trajectory the trajectory to check for collisions
     * @return the CollisionInfo object containing the closest collision point and collidable object,
     *         or null if no collision occurred
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Collidable> collidablesOnTrajectory = new ArrayList<>();
        for (Collidable i : collidables) {
            if (i.getCollisionRectangle().intersectionPoints(trajectory).size() != 0) {
                collidablesOnTrajectory.add((i));
            }
        }
        if (collidablesOnTrajectory.size() == 0) {
            return null;
        }
        Rectangle firstCollidable = collidablesOnTrajectory.get(0).getCollisionRectangle();
        Point closestPoint = trajectory.closestIntersectionToStartOfLine(firstCollidable);
        double minDistance = closestPoint.distance(trajectory.start());
        Collidable closestObject  =  collidablesOnTrajectory.get(0);
        for (Collidable i : collidablesOnTrajectory) {
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(i.getCollisionRectangle());
            if (collisionPoint.distance(trajectory.start()) < minDistance) {
                closestPoint = collisionPoint;
                minDistance =  closestPoint.distance(trajectory.start());
                closestObject  = i;
            }
        }
        return (new CollisionInfo(closestPoint, closestObject));
    }
}
