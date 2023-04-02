import bagel.Image;

/**
 * A Stockpile is a storage actor which has a fruit count starting at 0, and an update method.
 * Active actors can give and take fruit from a stockpile. The final fruit count of a stockpile
 * is printed if a simulation runs to completion.
 */
public class Stockpile extends StorageActor {

    private final String image = "res/images/cherries.png";
    private final Image stockpile;

    /**
     * Constructor for Stockpile object, inherits position construction, sets the fruit count to
     * 0 and instantiates the image object
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Stockpile(double x, double y) {
        super(x, y);
        setFruitCount(0);
        stockpile = new Image(image);
    }

    /**
     * Draws the stockpile to the screen
     */
    @Override
    public void draw() {
        stockpile.drawFromTopLeft(x, y);
        displayFruit();
    }
}
