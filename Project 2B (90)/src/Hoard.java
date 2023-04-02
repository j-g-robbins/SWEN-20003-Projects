import bagel.Image;

/**
 * A Hoard is a storage actor which has a fruit count starting at 0 and an update method. Active
 * actors can give and take fruit from a hoard. The final fruit count of a hoard is printed if a
 * simulation runs to completion.
 */
public class Hoard extends StorageActor {

    private final String image = "res/images/hoard.png";
    private final Image hoard;

    /**
     * Constructor for a Hoard, inherits position functionality, sets fruit count to 0
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Hoard(double x, double y) {
        super(x, y);
        setFruitCount(0);
        hoard = new Image(image);
    }

    /**
     * Draws a hoard and its fruit count to the screen
     */
    @Override
    public void draw() {
        hoard.drawFromTopLeft(x, y);
        displayFruit();
    }

}
