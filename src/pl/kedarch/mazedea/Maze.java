package pl.kedarch.mazedea;
import java.util.ArrayList;
import java.lang.Integer;

/**
 * Class containing data about maze map
 */
class Maze {
    /**
     * Array of elements on Maze
     */
    ArrayList<ArrayList<Integer>> elemTypes;
    /**
     * Array of attributes corresponding to elements on Maze
     */
    ArrayList<ArrayList<Integer>> elemAttribs;

    /**
     * Initializes ArrayLists
     */
    Maze() {
        this.elemTypes = new ArrayList<ArrayList<Integer>>();
        this.elemAttribs = new ArrayList<ArrayList<Integer>>();
    }

}
