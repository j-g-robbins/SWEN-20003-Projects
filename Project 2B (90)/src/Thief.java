import bagel.Image;

/**
 * A Thief is an active actor. Thieves move around and interact with various actors. A thief has
 * a state, which affects how it interacts with other actors. Thieves can be consuming, active,
 * carrying and destroyed.
 */
public class Thief extends ActiveActor {

    private final String image = "res/images/thief.png";
    private final Image thief;
    private boolean consuming = false;

    /**
     * Constructor for thief, inherits position functionality, sets direction to up and instantiates
     * the image object
     * @param x The x coordinate
     * @param y The y coordinate
     */
    public Thief(double x, double y) {
        super(x, y);
        setDirection(UP);
        thief = new Image(image);
    }

    /**
     * Gets the variable consuming, whether a thief is consuming or not
     * @return boolean The value of the variable consuming
     */
    public boolean isConsuming() {
        return consuming;
    }

    /**
     * Sets the value of the variable consuming
     * @param val The new value for the variable consuming
     */
    public void setConsuming(boolean val) {
        consuming = val;
    }

    /**
     * Draws thief on screen at position
     */
    public void draw() {
        thief.drawFromTopLeft(x, y);
    }

    /**
     * Method for thief logic for when standing on a storage, logic dependent on storage actor
     * type and thief state
     * @param store The storage actor on the same position
     */
    public void onStorage(StorageActor store) {
        // Logic for hoards
        if (store instanceof Hoard) {
            if (isConsuming()) {
                setConsuming(false);
                if (!isCarrying()) {
                    if (store.hasFruit()) {
                        toggleCarrying();
                        store.takeFruit();
                    } else {
                        rotate(RIGHT);
                    }
                }
            } else if (isCarrying()) {
                toggleCarrying();
                store.addFruit();
                rotate(RIGHT);
            }

        // Logic for stockpiles
        } else if (store instanceof Stockpile) {
            if (!isCarrying()) {
                if (store.hasFruit()) {
                    toggleCarrying();
                    setConsuming(false);
                    store.takeFruit();
                    rotate(RIGHT);
                }
            } else {
                rotate(RIGHT);
            }

        // Logic for trees
        } else if ((store instanceof Tree || store instanceof GoldenTree) && !isCarrying()) {
            if (store.hasFruit()) {
                store.takeFruit();
                toggleCarrying();
            }
        }
    }

    /**
     * Creates an actor of type Thief and returns it (Factory Method Pattern)
     * @param curr The current actor, of which the position will be copied
     * @return
     */
    public ActiveActor createActor(ActiveActor curr) {
        return new Thief(getX(), getY());
    }

    /**
     * Method to execute logic for when a thief is standing on gatherer, rotates 270 degrees clockwise
     */
    public void onGatherer() {
        rotate(3*RIGHT);
    }

    /**
     * Method to execute logic when thief is standing on pad, set consuming to true
     * @param pad The Pad object in the same position
     */
    public void onPad(Pad pad) {
        setConsuming(true);
    }

    /**
     * Update method for thief, calls move if the thief is active
     */
    @Override
    public void update() {
        if (isActive()) {
            move();
        }
    }

}
