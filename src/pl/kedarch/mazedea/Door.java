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
    /*
     * @see pl.kedarch.mazedea.MapElement#getImage()
     */
    @Override
    String getImage() {
        return "door.png";
    }
    /*
     * @see pl.kedarch.mazedea.MapElement#getImageToggled()
     */
    @Override
    String getImageToggled() {
        return "door_opened.png";
    }
}
