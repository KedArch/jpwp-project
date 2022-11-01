package pl.kedarch.mazedea;

/**
 * Class controlling maze functionality
 */
class MazeController {
    /**
     * Object containing loaded maps
     */
    MapLoader maps;

    /**
     * Create MapLoader object
     * @throws Exception if programmer forgot something
     */
    MazeController() throws Exception {
        this.maps = new MapLoader();
    }

    /**
     * Start maze functionality
     */
    void start() {
    }

    /**
     * Reload external maps
     * @throws Exception if programmer forgot something
     */
    void reloadMaps() throws Exception {
        this.maps.reload();
    }
}
