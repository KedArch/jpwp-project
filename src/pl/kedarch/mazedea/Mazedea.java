package pl.kedarch.mazedea;

/**
 * Class used to start app
 */
public class Mazedea {
    /**
     * Function starting the app
     * @param args
     * command line arguments
     */
    public static void main(final String[] args) {
        MazeController control = new MazeController();
        control.start();
    }
}
