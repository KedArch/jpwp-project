package pl.kedarch.mazedea;

/**
 * Class used to start app
 */
public class Main {
    /**
     * Function starting the app
     * @param args command line arguments
     * @throws Exception if map is invalid or programmer forgot something
     */
    public static void main(final String[] args) throws Exception {
        MapController control = new MapController();
        control.start(args);
    }
}
