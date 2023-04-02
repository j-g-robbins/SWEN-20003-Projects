import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * CSVReader safely opens a CSV file, checking its contents, and then parsing and initialising actors
 * from the fields. Actors are instantiated in 3 separate arrays to allow for extensibility with
 * actions from static actors.
 */
public class CSVReader {

    private static final String COMMA = ",";
    private final String fileName;
    private List<String[]> fields = new ArrayList<>();
    private String[] actors = {"signup", "signdown", "signleft", "signright", "fence", "pad", "pool", "stockpile",
            "hoard", "tree", "goldentree", "gatherer", "thief"} ;


    public CSVReader(String fileName) {
        this.fileName = fileName;
    }


    // Reads CSV file line by line into dynamic array and returns separated data
    public List<String[]> readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                fields.add(line.split(COMMA));
            }
        } catch (IOException | IllegalArgumentException | NullPointerException e) {
            System.out.println("error: file "+fileName+" not found");
            System.exit(-1);
        }
        return fields;
    }

    public void checkFile() {
        int i = 0;
        try {
            for (; i < fields.size(); i++) {
                if (!Arrays.asList(actors).contains(fields.get(i)[0].toLowerCase())) {
                    throw new Error();
                } else {
                    Integer.parseInt(fields.get(i)[1]);
                    Integer.parseInt(fields.get(i)[2]);
                    }
            }
        } catch (NumberFormatException | IndexOutOfBoundsException | Error e) {
            System.out.println("error: in file "+fileName+" at line "+(i+1));
            System.exit(-1);
        }
    }

    // Create static actors from CSV file
    public static List<StaticActor> getStatics(List<String[]> csvInput) {
        ArrayList<StaticActor> statics = new ArrayList<>();
        for (String[] line : csvInput) {
            switch (line[0].toLowerCase()) {
                case "signup":
                case "signdown":
                case "signleft":
                case "signright":
                    statics.add(new Sign(Double.parseDouble(line[1]), Double.parseDouble(line[2]), line[0].toLowerCase()));
                    break;
                case "fence":
                    statics.add(new Fence(Double.parseDouble(line[1]), Double.parseDouble(line[2])));
                    break;
                case "pad":
                    statics.add(new Pad(Double.parseDouble(line[1]), Double.parseDouble(line[2])));
                    break;
                case "pool":
                    statics.add(new Pool(Double.parseDouble(line[1]), Double.parseDouble(line[2])));
                    break;
            }
        }
        statics.forEach(Actor::fixate);
        return statics;
    }

    // Create storage actors from CSV file
    public static List<StorageActor> getStores(List<String[]> csvInput) {
        List<StorageActor> stores = new ArrayList<>();
        for (String[] line : csvInput) {
            switch (line[0].toLowerCase()) {
                case "stockpile":
                    stores.add(new Stockpile(Double.parseDouble(line[1]), Double.parseDouble(line[2])));
                    break;
                case "hoard":
                    stores.add(new Hoard(Double.parseDouble(line[1]), Double.parseDouble(line[2])));
                    break;
                case "tree":
                    stores.add(new Tree(Double.parseDouble(line[1]), Double.parseDouble(line[2])));
                    break;
                case "goldentree":
                    stores.add(new GoldenTree(Double.parseDouble(line[1]), Double.parseDouble(line[2])));
                    break;
            }
        }
        stores.forEach(Actor::fixate);
        return stores;
    }

    // Create active actors from CSV file
    public static ArrayList<ActiveActor> getActives(List<String[]> csvInput) {
        ArrayList<ActiveActor> actives = new ArrayList<>();
        for (String[] line : csvInput) {
            switch (line[0].toLowerCase()) {
                case "gatherer":
                    actives.add(new Gatherer(Double.parseDouble(line[1]), Double.parseDouble(line[2])));
                    break;
                case "thief":
                    actives.add(new Thief(Double.parseDouble(line[1]), Double.parseDouble(line[2])));
                    break;
            }
        }
        actives.forEach(Actor::fixate);
        return actives;
    }

}
