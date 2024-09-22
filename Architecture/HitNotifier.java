package Architecture;

/**
 * The HitNotifier interface represents an object that can notify listeners about hit events.
 * Implementing classes allow other objects to register as listeners and receive notifications
 * when a hit event occurs.
 */
public interface HitNotifier {
    /**
     * Adds the specified HitListener to the list of listeners to be notified about hit events.
     * @param hl the HitListener to be added
     */
    void addHitListener(HitListener hl);
    /**
     * Removes the specified HitListener from the list of listeners.
     * After removal, the listener will no longer be notified about hit events.
     * @param hl the HitListener to be removed
     */
    void removeHitListener(HitListener hl);

}
