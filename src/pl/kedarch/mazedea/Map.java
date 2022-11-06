package pl.kedarch.mazedea;
import java.util.ArrayList;

/**
 * Class containing data about map
 */
class Map {
    /**
     * Array of elements on Map
     */
    private ArrayList<ArrayList<MapElement>> elemTypes;
    /**
     * Player class containing his and exit coordinates
     */
    private Player player;
    /**
     * Map name
     */
    private String name;

    /**
     * Initializes ArrayLists
     */
    Map() {
        this.elemTypes = new ArrayList<ArrayList<MapElement>>();
        this.player = new Player();
    }

    /**
     * @return elemTypes '2D' list of MapElements
     */
    ArrayList<ArrayList<MapElement>> getElemTypes() {
        return elemTypes;
    }

    /**
     * @param elemTypes '2D' list of MapElements
     */
    void setElemTypes(ArrayList<ArrayList<MapElement>> elemTypes) {
        this.elemTypes = elemTypes;
    }

    /**
     * @return Player class containing data about player and end positions
     */
    Player getPlayer() {
        return player;
    }

    /**
     * @param player class containing data about player and end positions
     */
    void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return name
     */
    String getName() {
        return name;
    }

    /**
     * @param name
     */
    void setName(String name) {
        this.name = name;
    }


}
