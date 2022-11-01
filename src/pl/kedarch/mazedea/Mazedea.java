package pl.kedarch.mazedea;

/**
 * Class used to start app
 */
public class Mazedea {
    /**
     * Function starting the app
     * @param args command line arguments
     * @throws Exception if programmer forgot something
     */
    public static void main(final String[] args) throws Exception {
        MazeController control = new MazeController();
        control.start();
    }
}
