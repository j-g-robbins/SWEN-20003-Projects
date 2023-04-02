import java.util.ArrayList;
import java.util.Random;

/**
 * An active actor is an actor that can move and interact with other actors, such as gatherers
 * and thieves. Active actors have an active and carrying status, and can be destroyed. Actors
 * also have interaction logic with other actors depending on the object and both of their states.
 */
public abstract class ActiveActor extends Actor {

    private static final int TURN = 90;
    private Random random = new Random();
    private int direction;
    private boolean carrying = false;
    private boolean active = true;
    private boolean destroyed = false;

    /**
     * Inherited constructor for ActiveActor
     * @param x This is the x coordinate of the actor
     * @param y This is the y coordinate of the actor
     */
    public ActiveActor(double x, double y) {
        super(x, y);
    }

    /**
     * Method to check whether an actor is destroyed
     * @return boolean Boolean indicating whether an actor is destroyed
     */
    public boolean isDestroyed() {
        return destroyed;
    }

    /**
     * Method to toggle the destroyed state of an actor to true
     */
    public void toggleDestroyed() {
        destroyed = true;
    }

    /**
     * Method to check if actor is carrying
     * @return boolean Boolean indicating whether an actor is carrying
     */
    public boolean isCarrying() {
        return carrying;
    }

    /**
     * Method to check if actor is active
     * @return boolean Boolean indicating whether an actor is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Method to toggle the active state of an actor
     */
    public void toggleActive() {
        active = !active;
    }

    /**
     * Method to toggle the carrying state of an actor
     */
    public void toggleCarrying() {
        carrying = !carrying;
    }

    /**
     * Method to set the direction of the actor to input value
     * @param angle New direction for the actor
     */
    public void setDirection(int angle) {
        direction = angle;
    }

    /**
     * Gets the direction of an actor
     * @return int Returns the direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Method to rotate the direction of an actor by given amount
     * @param amount The amount for the direction to be rotated by
     */
    public void rotate(int amount) {
        direction += amount;
        direction %= 360;
    }

    /**
     * Method for logic when active actor is on the same position as a storage actor
     * @param store The storage actor on the same position
     */
    public abstract void onStorage(StorageActor store);

    /**
     * Method for logic when active actor is on the same position as a static actor, calls
     * the relevant method depending on static actor type
     * @param actor The static actor on the same position
     */
    public void onStatic(StaticActor actor) {
        if (actor instanceof Fence) {
            onFence((Fence)actor);
        } else if (actor instanceof Sign) {
            onSign((Sign)actor);
        } else if (actor instanceof Pad) {
            onPad((Pad)actor);
        }
    }

    /**
     * Method for logic when active actor is on the same position as a fence
     * @param fence The fence on the same position
     */
    public void onFence(Fence fence) {
        toggleActive();
        rotate(180);
        move();
    }

    /**
     * Method to create an actor of a given type, uses the Factory Method Pattern
     * @param curr The current actor, of which the position will be copied
     * @return ActiveActor This is the active actor that is created
     */
    public abstract ActiveActor createActor(ActiveActor curr);

    /**
     * Method for logic when active actor is on the same position as a Mitosis Pool,
     * calls createActor using Factory Method Pattern and adds the actors into the current list
     * @param actives Returns the current list of active actors with the new actors added
     */
    public void onPool(ArrayList<ActiveActor> actives) {
        ActiveActor actor1 = createActor(this);
        actor1.setDirection(getDirection());
        actor1.rotate(270);
        ActiveActor actor2 = createActor(this);
        actor2.setDirection(getDirection());
        actor2.rotate(90);
        actives.add(actor1);
        actives.add(actor2);
        toggleDestroyed();
    }

    /**
     * Sets an actor's direction to same direction as the sign
     * @param sign The sign in the same position as the active actor
     */
    public void onSign(Sign sign) {
        setDirection(sign.getDirection());
    }

    /**
     * Method to be implemented by subclasses for logic when standing on a Pad
     * @param pad The Pad object in the same position
     */
    public void onPad(Pad pad) {}

    /**
     * Method to be implemented by thief for logic when standing on a Gatherer
     */
    public void onGatherer() { }

    /**
     * Moves an actor one tile in current direction
     */
    public void move() {
        if (direction == UP) {
            y -= TILE_LENGTH;
        } else if (direction == RIGHT) {
            x += TILE_LENGTH;
        } else if (direction == DOWN) {
            y += TILE_LENGTH;
        } else {
            x -= TILE_LENGTH;
        }
    }

    /**
     * Calls the update method, used when a tick has passed
     */
    public void tick() {
        update();
    }

}
