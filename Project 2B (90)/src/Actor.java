/**
 * An actor is any object in the game ShadowLife, every actor has a position,
 * is fixated into a tile, can be compared by position and can be updated
 */

public abstract class Actor implements Comparable<Actor> {

    protected double x, y;
    protected static final int TILE_LENGTH = 64;
    protected static final int UP = 0;
    protected static final int RIGHT = 90;
    protected static final int DOWN = 180;
    protected static final int LEFT = 270;

    public Actor(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get the x coordinate of the actor
     * @return double The x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * Get the y coordinate of the actor
     * @return double The y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     *  Calls the update method for an actor, to be used when a tick has passed
     */
    public void tick() {
        update();
    }

    /**
     * Moves an actor
     */
    public void move() { }

    /**
     * Draws an actor to the screen
     */
    public void draw() { }

    /**
     * Method to compare two actors by their positions
     * @param object This is the other actor to be compared to
     * @return int Returns an integer, zero if actors are in the same position
     */
    public int compareTo(Actor object) {
        if (getX() - object.getX() == 0) {
            return ((int)(getY() - object.getY()));
        }
        return -1;
    }

    /**
     * Method to fixate an actor into a well-defined tile (64x64)
     */
    protected void fixate() {
        if (x % TILE_LENGTH >= (TILE_LENGTH/2.0)) {
            x += (TILE_LENGTH -(x % TILE_LENGTH));
        } else {
            x -= (x % TILE_LENGTH);
        }
        if (y % TILE_LENGTH >= (TILE_LENGTH/2.0)) {
            y += (TILE_LENGTH - (y % TILE_LENGTH));
        } else {
            y -= (y % TILE_LENGTH);
        }
    }

    /**
     * Method to update an actors state
     */
    public void update() {}
}
