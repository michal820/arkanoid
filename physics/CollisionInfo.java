package physics;

import Primitive.Point;
/**
 * The CollisionInfo class represents information about a collision between objects.
 * It stores the collision point and the collidable object involved in the collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;
    /**
     *Constructs a CollisionInfo object with the specified collision point and collidable object.
     * @param p the point at which the collision occurs
     * @param c the collidable object involved in the collision
     */
    public CollisionInfo(Point p, Collidable c) {
        collisionPoint = p;
        collisionObject = c;
    }
    /**
     * Returns the point at which the collision occurs.
     * @return the collision point
     */
    public Point getCollisionPoint() {
        return  collisionPoint;
    }
    /**
     * Returns the collidable object involved in the collision.
     * @return the collision object
     */
    public Collidable getCollisionObject() {
        return collisionObject;
    }


}