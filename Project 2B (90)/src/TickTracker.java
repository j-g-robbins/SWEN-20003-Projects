/**
 * A TickTracker tracks the frequency and number of ticks that have passed in a given simulation,
 * using values given in the command line. TickTracker tracks the number of ticks passed and whether
 * a new tick has occurred.
 */
public class TickTracker {

    private final int tickRate;
    private long lastUpdate;
    private int tickCount;

    /**
     * Constructor for TickTracker, sets values for last update, tick count and tick rate
     * @param lastUpdate The time of the last update in milliseconds
     * @param tickCount The number of ticks that have passed
     * @param tickRate The time between each tick in milliseconds
     */
    public TickTracker(long lastUpdate, int tickCount, int tickRate) {
        this.lastUpdate = lastUpdate;
        this.tickCount = tickCount;
        this.tickRate = tickRate;
    }

    /**
     * Gets the tick count
     * @return int The tick count, number of ticks passed
     */
    public int getTickCount() {
        return tickCount;
    }

    /**
     * Method that calculates whether a tick has passed since the last update
     * @return Boolean indicating whether a tick has passed or not
     */
    public boolean newTick() {
        boolean isNewTick = false;

        if ((System.currentTimeMillis() - lastUpdate) >= tickRate) {
            isNewTick = true;
            tickCount += 1;
            lastUpdate = System.currentTimeMillis();
        }

        return isNewTick;
    }

}
