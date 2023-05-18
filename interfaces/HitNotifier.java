
package interfaces;

/**
 * this interface indicate that objects that implement it send notifications when they are being hit.
 */
public interface HitNotifier {
    /**
     * this method add hl as a listener to hit events.
     * @param hl the interfaces.HitListener to add
     */
    void addHitListener(HitListener hl);
    /**
     * this method remove hl from the list of listeners to hit events.
     * @param hl the interfaces.HitListener to add
     */
    void removeHitListener(HitListener hl);
}
