import bagel.Image;

/**
 * A Pad is a static actor which has no update method. Static actors can only be drawn and compared
 * with by position. When a thief lands on a Pad, consuming is set to true for the thief.
 */

public class Pad extends StaticActor {

    private final String image = "res/images/pad.png";
    private final Image pad;

    /**
     * Constructor for Pad object, inherits position construction and instantiates image object
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Pad(double x, double y) {
        super(x, y);
        pad = new Image(image);
    }

    /**
     * Draws the pad to the screen
     */
    @Override
    public void draw() {
        pad.drawFromTopLeft(x, y);
    }

}
