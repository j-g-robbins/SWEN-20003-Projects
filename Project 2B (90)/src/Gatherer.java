import bagel.Image;

/**
 * A Gatherer is an active actor. Gatherers move around and interact with various actors. A gatherer
 * has a state, which affects how it interacts with other actors. Gatherers can be active, carrying
 * and destroyed.
 */
public class Gatherer extends ActiveActor {

    private final String image = "res/images/gatherer.png";
    private final Image gatherer;

    /**
     * Constructor for gatherer, inherits position functionality, sets direction to left and
     * instantiates image
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Gatherer(double x, double y) {
        super(x, y);
        setDirection(LEFT);
        gatherer = new Image(image);
    }

    /**
     * Draws the gatherer to the screen
     */
    public void draw() {
        gatherer.drawFromTopLeft(x, y);
    }

    /**
     * Implements gatherer logic for standing on a storage actor depending on the type of
     * actor and the state of the gatherer and the actor
     * @param store The storage actor on the same position
     */
    public void onStorage(StorageActor store) {
        // Logic for trees
        if ((store instanceof Tree || store instanceof GoldenTree) && !isCarrying()) {
            if (store.hasFruit()) {
                store.takeFruit();
                toggleCarrying();
                rotate(DOWN);
            }

        // Logic for stockpiles and hoards
        } else if ((store instanceof Stockpile || store instanceof Hoard)) {
            if (isCarrying()) {
                toggleCarrying();
                store.addFruit();
            }
            rotate(DOWN);
        }
    }

    /**
     * Creates a new actor of Gatherer type (Factory Method Pattern) and returns it
     * @param curr The current actor, of which the position will be copied
     * @return ActiveActor The new Gatherer object created
     */
    public ActiveActor createActor(ActiveActor curr) {
        return new Gatherer(curr.getX(), curr.getY());
    }

    /**
     * Update method for the gatherer, if active calls the move method
     */
    @Override
    public void update() {
        if (isActive()) {
            move();
        }
    }


}
