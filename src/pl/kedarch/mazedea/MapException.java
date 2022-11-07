package pl.kedarch.mazedea;

/**
 * Exception instance used to detect invalid map files
 */
class MapException extends Exception {
    /**
     * Initializes class
     */
    MapException() {
        super();
    }
    /**
     * Initializes class
     * @param str message
     */
    MapException(String str) {
        super(str);
    }
}
