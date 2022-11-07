package pl.kedarch.mazedea;
import java.util.ArrayList;

/**
 * Class holding important info about player
 */
class Player extends MapElement {
    /*
     * @see pl.kedarch.mazedea.MapElement#getTermRepresentation()
     */
    @Override
    String getTermRepresentation() {
        return "P";
    }
    /**
     * An array containing coordinates of player<br>
     * Arguments are playerX, playerY, in that sequence
     */
    private Integer[] coords;
    /**
     * An array containing keys obtained by player
     */
    private ArrayList<MapElement> keys;

    /**
     * Initializes variables
     */
    Player() {
        this.coords = new Integer[2];
        this.keys = new ArrayList<MapElement>();
    }

    /**
     * @return keys ArrayList of owned keys
     */
    ArrayList<MapElement> getKeys() {
        return keys;
    }

    /**
     * @param keys ArrayList of owned keys
     */
    void setKeys(ArrayList<MapElement> keys) {
        this.keys = keys;
    }

    /**
     * @return coords ArrayList of player coordinates
     */
    Integer[] getCoords() {
        return coords;
    }

    /**
     * @param coords ArrayList of player coordinates
     */
    void setCoords(Integer[] coords) {
        this.coords = coords;
    }
}
