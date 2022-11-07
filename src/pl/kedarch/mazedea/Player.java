package pl.kedarch.mazedea;
import java.util.ArrayList;

/**
 * Class holding important info about player
 */
class Player extends MapElement {
    /**
     * An array containing coordinates of player and exit<br>
     * Arguments are playerX, playerY, endX, endY in that sequence
     */
    private Integer[] coords;
    /**
     * An array containing keys obtained by player
     */
    private ArrayList<MapElement> keys;

    /**
     * Initializes coords array
     */
    Player() {
        this.coords = new Integer[2];
        this.keys = new ArrayList<MapElement>();
    }

    /**
     * @return keys
     */
    ArrayList<MapElement> getKeys() {
        return keys;
    }

    /**
     * @param keys
     */
    void setKeys(ArrayList<MapElement> keys) {
        this.keys = keys;
    }

    /**
     * @return coords
     */
    Integer[] getCoords() {
        return coords;
    }

    /**
     * @param coords
     */
    void setCoords(Integer[] coords) {
        this.coords = coords;
    }
}
