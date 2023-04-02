import bagel.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ShadowLife is a game where thieves and gatherers interact with various other actors and each
 * other. Gatherers collect fruit from trees and store it in stockpiles and hoards, thieves take fruit
 * from trees, hoards and stockpiles and move it to other hoards
 * @author Jasper Robbins
 */
public class ShadowLife extends AbstractGame {

    // Define constants
    private static String csvFile;
    private static int tickRate;
    private static int maxTicks;

    // Initialise time tracker
    TickTracker tickTracker = new TickTracker(System.currentTimeMillis()-tickRate, -1, tickRate);

    // Initialise game objects
    private final Background background = new Background();
    private final List<StorageActor> storageActors;
    private final ArrayList<Actor> world = new ArrayList<>();
    private final ArrayList<ActiveActor> activeActors;
    private final ArrayList<ActiveActor> extraActives = new ArrayList<>();

    /**
     * Constructor for the game ShadowLife, reads a CSV file using CSVReader and parses the fields,
     * then creates and initialises the actors from the fields into the world list for active actors
     */
    public ShadowLife() {
        super();

        // Reads CSV file and parse fields
        List<String[]> fields;
        CSVReader reader = new CSVReader(csvFile);
        fields = reader.readFile();
        reader.checkFile();

        // Initialise actors and world from CSV fields, statics and storages separated for extensibility
        List<StaticActor> staticActors = CSVReader.getStatics(fields);
        activeActors = CSVReader.getActives(fields);
        storageActors = CSVReader.getStores(fields);
        world.addAll(staticActors);
        world.addAll(storageActors);

    }

    /**
     * Main method for the game ShadowLife, takes command line arguments and parses using
     * CommandLineArgs, initialising the respective values, then runs the game ShadowLife
     * @param args Command line arguments, usage: ShadowLife <tick rate> <max ticks> <world file>
     */
    public static void main(String[] args) {

        // Parse command line arguments
        CommandLineArgs worldInfo = new CommandLineArgs(args);
        worldInfo.checkArgs();
        tickRate = worldInfo.getTickRate();
        csvFile = worldInfo.getWorldFile();
        maxTicks = worldInfo.getMaxTicks();

        // Run game
        ShadowLife game = new ShadowLife();
        game.run();
    }

    /**
     * Update method for the game, checks conditions of the actors and updates them correspondingly,
     * tracks ticks using TickTracker and checks for simulation end, updates actor states depending
     * on positions of actors, then draws all actors to screen.
     * @param input Optional game input
     */
    @Override
    public void update(Input input) {

        boolean onPool = false;
        boolean running = false;

        // Track ticks, check for simulation end, update active actors
        if (tickTracker.newTick()) {

            // Check if any actors remain active
            for (ActiveActor actor : activeActors) {
                if (actor.isActive()) {
                    running = true;
                    break;
                }
            }

            // Halt simulation if no actors remain
            if (!running) {
                System.out.println(tickTracker.getTickCount()+" ticks");
                for (StorageActor store : storageActors) {
                    if (store instanceof Stockpile || store instanceof Hoard) {
                        System.out.println(store.getFruitCount());
                    }
                }
                System.exit(0);
            }

            // Halt simulation if timed out
            if (tickTracker.getTickCount() == maxTicks) {
                System.out.println("Timed out");
                System.exit(-1);
            }

            // Actors perform action for tick
            activeActors.forEach(ActiveActor::tick);


            for (int i = 0; i < activeActors.size(); i++) {

                // Detect actors standing on objects, perform actions
                for (Actor object : world) {
                    if (activeActors.get(i).compareTo(object) == 0) {
                        if (object instanceof StorageActor) {
                            activeActors.get(i).onStorage((StorageActor) object);
                        } else if (object instanceof Pool) {
                            activeActors.get(i).onPool(extraActives);
                            onPool = true;
                        } else {
                            activeActors.get(i).onStatic((StaticActor) object);
                        }
                    }
                }

                // Detect thieves standing on gatherers, perform actions
                for (ActiveActor activeActor : activeActors) {
                    if (activeActors.get(i) instanceof Thief &&
                            activeActor instanceof Gatherer &&
                            activeActors.get(i).compareTo(activeActor) == 0) {
                        activeActors.get(i).onGatherer();
                    }
                }
            }

            // Add any actors created from mitosis pools
            if (onPool) {
                activeActors.addAll(extraActives);
                extraActives.clear();
            }
        }

        // Remove any destroyed actors
        for (int i = 0; i < activeActors.size(); i++) {
            if (activeActors.get(i).isDestroyed()) {
                activeActors.remove(i);
            }
        }

        // Draw elements of game in desired order
        background.draw();
        world.forEach(Actor::draw);
        activeActors.forEach(ActiveActor::draw);
    }
}
