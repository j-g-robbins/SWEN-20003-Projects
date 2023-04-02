/**
 * Static actors do not move or update any state during a tick, they simply exist to be interacted
 * with by active actors. This can be extended easily to add functionality.
 */
public abstract class StaticActor extends Actor {

    /**
     * Inherited constructor for StaticActor
     * @param x This is the x coordinate of the actor
     * @param y This is the y coordinate of the actor
     */
    public StaticActor(double x, double y) {
        super(x, y);
    }
}
