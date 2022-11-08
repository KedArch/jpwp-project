package pl.kedarch.mazedea;

/**
 * Element toggling gates with the same link
 */
class Level extends Toggleable {
    /*
     * @see pl.kedarch.mazedea.MapElement#getTermRepresentation()
     */
    @Override
    String getTermRepresentation() {
        return "L";
    }
    /*
     * @see pl.kedarch.mazedea.MapElement#getImage()
     */
    @Override
    String getImage() {
        return "level.png";
    }
    /*
     * @see pl.kedarch.mazedea.MapElement#getImageToggled()
     */
    @Override
    String getImageToggled() {
        return "level_toggled.png";
    }
}
