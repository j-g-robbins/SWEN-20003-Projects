import bagel.Font;

/**
 * Storage actors have a fruit count, and can be interacted with by active actors. Storage actors
 * give fruit to actors and may store fruit given by actors too. The fruit count may be drawn
 * on screen for a storage actor.
 */
public abstract class StorageActor extends Actor {

    private int fruitCount = 0;
    protected final Font font = new Font("res/VeraMono.ttf", 24);

    /**
     * Inherited constructor for StorageActor
     * @param x This is the x coordinate of the actor
     * @param y This is the y coordinate of the actor
     */
    public StorageActor(double x, double y) {
        super(x, y);
    }

    /**
     * Setter for the fruit count for the actor
     * @param fruitCount This is the number of fruit the actor will have
     */
    public void setFruitCount(int fruitCount) {
        this.fruitCount = fruitCount;
    }

    /**
     * Getter for the number of fruit the actor currently has
     * @return int This is the fruit count
     */
    public int getFruitCount() {
        return fruitCount;
    }

    /**
     * Method used to decrement fruit count by 1 when an active actor takes fruit
     */
    public void takeFruit() {
        fruitCount -= 1;
    }

    /**
     * Method used to increment fruit count by 1 when an active actor gives fruit
     */
    public void addFruit() {
        fruitCount += 1;
    }

    /**
     * Method ot display the fruit count on screen at the top left of actor
     */
    public void displayFruit() {
        font.drawString(""+getFruitCount(), x, y);
    }

    /**
     * Method to check if the storage actor has a fruit count not equal to 0
     * @return returns a boolean indicating whether the storage actor has any fruit to be taken
     */
    public boolean hasFruit() {
        return fruitCount != 0;
    }
}
