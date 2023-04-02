import bagel.Image;

/**
 * A Tree is a storage actor from which active actors can harvest fruit. A tree starts with 3 fruit.
 */
public class Tree extends StorageActor {

    private final String image = "res/images/tree.png";
    private final Image tree;

    /**
     * Constructor for Tree object, inherits position construction, initialises the fruit count to
     * 3 and instantiates the image object
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Tree(double x, double y) {
        super(x, y);
        setFruitCount(3);
        tree = new Image(image);
    }

    /**
     * Draws tree object to screen
     */
    @Override
    public void draw() {
        tree.drawFromTopLeft(x, y);
        displayFruit();
    }
}
