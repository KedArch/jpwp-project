package pl.kedarch.mazedea;

/**
 * Element which can be opened by linked key
 */
class Door extends Toggable {
    /*
     * @see pl.kedarch.mazedea.MapElement#getTermRepresentation()
     */
    @Override
    String getTermRepresentation() {
        return "D";
    }
}
