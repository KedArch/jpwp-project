package pl.kedarch.mazedea;

/**
 * Element describing exit
 */
class Exit extends MapElement {
    /*
     * @see pl.kedarch.mazedea.MapElement#getTermRepresentation()
     */
    @Override
    String getTermRepresentation() {
        return "E";
    }
    /*
     * @see pl.kedarch.mazedea.MapElement#getImage()
     */
    @Override
    String getImage() {
        return "exit.png";
    }
    /*
     * @see pl.kedarch.mazedea.MapElement#getImageToggled()
     */
    @Override
    String getImageToggled() {
        return "";
    }
}
