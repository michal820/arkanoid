package physics;

import Primitive.Point;
import Primitive.Rectangle;
import Sprites.Ball;
/**
 * The Collidable interface represents an object that can participate in collisions.
 * It defines methods for retrieving the collision shape and handling collisions.
 */
public interface Collidable {
    /**
     * Returns the collision rectangle of the object.
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred at the given collision point
     * with the current velocity. Calculates and returns the new velocity
     * expected after the hit, based on the force inflicted on the object.
     * @param collisionPoint the point of collision
     * @param currentVelocity the current velocity of the object
     * @param hitter the ball itself.
     * @return the new velocity after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}
