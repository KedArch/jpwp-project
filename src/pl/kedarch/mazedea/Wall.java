package pl.kedarch.mazedea;

/**
 * Element blocking player
 */
class Wall extends MapElement {
    /*
     * @see pl.kedarch.mazedea.MapElement#getTermRepresentation()
     */
    String getTermRepresentation() {
        return "=";
    }
}
