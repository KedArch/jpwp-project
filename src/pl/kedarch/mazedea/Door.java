package pl.kedarch.mazedea;

/**
 * Element which can be opened by linked key
 */
class Door extends Toggleable {
    /*
     * @see pl.kedarch.mazedea.MapElement#getTermRepresentation()
     */
    @Override
    String getTermRepresentation() {
        return "D";
    }
}
