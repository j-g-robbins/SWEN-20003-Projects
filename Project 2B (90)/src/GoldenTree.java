import bagel.Image;

/**
 * A Golden Tree is a storage actor from which active actors can harvest fruit.
 * Golden trees have unlimited fruit.
 */
public class GoldenTree extends StorageActor {

    private final Image goldTree = new Image("res/images/gold-tree.png");

    /**
     * Method implemented by subclasses to display the fruit count if necessary
     */
    public void displayFruit() {}

    /**
     * Constructor for GoldenTree object, inherits position construction, sets the fruit count to
     * -1 for unlimited fruit and instantiates the image object
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public GoldenTree(double x, double y) {
        super(x, y);
        setFruitCount(-1);
        goldTree = new Image(image);
    }

    /**
     * Draws a golden tree to the screen
     */
    @Override
    public void draw() {
        goldTree.drawFromTopLeft(x, y);
    }

}




