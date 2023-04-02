import bagel.Image;

/**
 * A Sign is a static actor which has no update method. Static actors can only be drawn and compared
 * with by position. When an active actor stands on a sign, its direction is set to that of the sign.
 */
public class Sign extends StaticActor {

    private String image = "res/images/";
    private final Image sign;
    private int direction;

    /**
     * Constructor for a Sign, inherits position functionality, initializes the direction of the arrow
     * and forms the string to retrieve corresponding sign image
     * @param x The x coordinate
     * @param y The y coordinate
     * @param arrow The direction of the arrow of the sign
     */
    public Sign(double x, double y, String arrow) {
        super(x, y);
        arrow = arrow.substring(4);
        image = image + arrow + ".png";
        sign = new Image(image);
        setDirection(arrow);
    }

    /**
     * Gets the direction of the sign
     * @return int The direction of the sign
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Sets the direction of the sign in degrees
     * @param arrow The direction of the sign as a string
     */
    public void setDirection(String arrow) {
        switch (arrow) {
            case "up":
                direction = UP;
                break;
            case "right":
                direction = RIGHT;
                break;
            case "down":
                direction = DOWN;
                break;
            case "left":
                direction = LEFT;
                break;
        }
    }

    /**
     * Draws a sign to the screen
     */
    @Override
    public void draw() {
        sign.drawFromTopLeft(x, y);
    }


}
