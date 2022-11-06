package pl.kedarch.mazedea;

/**
 * Class controlling map functionality
 */
class MapController {
    /**
     * Object containing loaded maps
     */
    MapLoader maps;

    /**
     * Create MapLoader object
     * @throws Exception if programmer forgot something
     */
    MapController() throws Exception {
        this.maps = new MapLoader();
    }

    /**
     * Start map functionality
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
