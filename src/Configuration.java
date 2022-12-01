import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class Configuration {
    private static final String CONFIGURATION_FILE_NAME = "configuration.properties";
    private static final String NUMBER_OF_TILES_KEY = "numberOfTiles";
    private static final String WORLD_WIDTH_KEY = "worldWidth";
    private static final String WORLD_HEIGHT_KEY = "worldHeight";
    private static final String DRAW_GRID_KEY = "drawGrid";

    private static int numberOfTiles;
    private static int worldWidth;
    private static int worldHeight;
    private static boolean drawGrid;

    private static boolean alreadyCalled = false;

    public static void getConfiguration() {
        if(!alreadyCalled){
            alreadyCalled = true;
            Properties properties = new Properties();
            try {
                ClassLoader loader = Thread.currentThread().getContextClassLoader();
                InputStream stream = loader.getResourceAsStream(CONFIGURATION_FILE_NAME);
                properties.load(stream);

            } catch (IOException e) {
                System.err.println("Error reading " + CONFIGURATION_FILE_NAME + ".");
                throw new RuntimeException(e);
            }

            try{
                numberOfTiles = Integer.parseInt(properties.getProperty(NUMBER_OF_TILES_KEY));
                worldHeight = Integer.parseInt(properties.getProperty(WORLD_WIDTH_KEY));
                worldWidth = Integer.parseInt(properties.getProperty(WORLD_HEIGHT_KEY));
                drawGrid = Boolean.parseBoolean(properties.getProperty(DRAW_GRID_KEY));
            } catch(java.lang.NumberFormatException e){
                System.err.println("Some properties are in the wrong format in file " + CONFIGURATION_FILE_NAME + ".");
                throw new RuntimeException(e);
            }
        }

    }

    public static int getNumberOfTiles() {
        getConfigIfNeeded();
        return numberOfTiles;
    }

    public static int getWorldWidth() {
        getConfigIfNeeded();
        return worldWidth;
    }

    public static int getWorldHeight() {
        getConfigIfNeeded();
        return worldHeight;
    }

    public static boolean isDrawGrid() {
        getConfigIfNeeded();
        return drawGrid;
    }

    private static void getConfigIfNeeded() {
        if(!alreadyCalled){
            getConfiguration();
        }
    }
}
