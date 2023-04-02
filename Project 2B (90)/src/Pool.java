import bagel.Image;

/**
 * A Mitosis Pool is a static actor which has no update method. Static actors can only be drawn and
 * compared with by position. When an active actor stands on a mitosis pool, it is destroyed, and
 * two other instances of the actor are created, going in opposite perpendicular directions to the
 * initial actor.
 */
public class Pool extends StaticActor {

    private final String image = "res/images/pool.png";
    private final Image mitosisPool;

    /**
     * Constructor for Pad, inherits position construction
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Pool(double x, double y) {
        super(x, y);
        mitosisPool = new Image(image);
    }

    /**
     * Draws mitosis pool to the screen
     */
    @Override
    public void draw() {
        mitosisPool.drawFromTopLeft(x, y);
    }

}
