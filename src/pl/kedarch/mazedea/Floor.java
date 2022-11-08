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
    /*
     * @see pl.kedarch.mazedea.MapElement#getImage()
     */
    @Override
    String getImage() {
        return "floor.png";
    }
    /*
     * @see pl.kedarch.mazedea.MapElement#getImageToggled()
     */
    @Override
    String getImageToggled() {
        return "";
    }
}
