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
    /*
     * @see pl.kedarch.mazedea.MapElement#getImage()
     */
    @Override
    String getImage() {
        return "wall.png";
    }
    /*
     * @see pl.kedarch.mazedea.MapElement#getImageToggled()
     */
    @Override
    String getImageToggled() {
        return "";
    }
}
