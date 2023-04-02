/**
 * CommandLineArgs is a class that parses the command lines given to ShadowLife, checking for
 * validity. It reads the tick rate, max number of ticks and world file name.
 */
public class CommandLineArgs {

    private int tickRate;
    private int maxTicks;
    private String worldFile;

    public CommandLineArgs(String[] args) {

        // Check number of arguments and validity of integers
        try {
            tickRate = Integer.parseInt(args[0]);
            maxTicks = Integer.parseInt(args[1]);
            worldFile = args[2];
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        }
    }


    public void checkArgs() {
        // Checks tick rate and max tick number validity
        if (tickRate < 0 || maxTicks < 0) {
            System.out.println("usage: ShadowLife <tick rate> <max ticks> <world file>");
            System.exit(-1);
        }
    }

    public int getTickRate() {
        return tickRate;
    }

    public int getMaxTicks() {
        return maxTicks;
    }

    public String getWorldFile() {
        return worldFile;
    }

}