import bagel.Image;

/**
 * A Fence is a static actor which has no update method. Static actors can only be drawn and compared
 * with by position. When a active actor stands on a fence, active is set to false for the active actor.
 */
public class Fence extends StaticActor {

    private final String image = "res/images/fence.png";
    private final Image fence;

    /**
     * Constructor for Fence object, inherits position construction and instantiates the image object
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Fence(double x, double y) {
        super(x, y);
        fence = new Image(image);
    }

    /**
     * Draws the fence to the screen
     */
    @Override
    public void draw() {
        fence.drawFromTopLeft(x, y);
    }

}
