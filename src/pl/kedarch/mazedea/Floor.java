package pl.kedarch.mazedea;

/**
 * Class which does not block player
 */
class Floor extends MapElement {
    /*
     * @see pl.kedarch.mazedea.MapElement#getTermRepresentation()
     */
    @Override
    String getTermRepresentation() {
        return "-";
    }
}
