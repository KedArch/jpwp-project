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
    private int[] coords;
    /**
     * An array containing keys obtained by player
     */
    private ArrayList<Key> keys;

    /**
     * Initializes coords array
     */
    Player() {
        this.coords = new int[4];
    }

    /**
     * @return keys
     */
    public ArrayList<Key> getKeys() {
        return keys;
    }

    /**
     * @param keys
     */
    public void setKeys(ArrayList<Key> keys) {
        this.keys = keys;
    }

    /**
     * @return coords
     */
    int[] getCoords() {
        return coords;
    }

    /**
     * @param coords
     */
    void setCoords(int[] coords) {
        this.coords = coords;
    }
}
