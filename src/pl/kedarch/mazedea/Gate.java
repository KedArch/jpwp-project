package pl.kedarch.mazedea;

/**
 * Element describing gates which can be toggled by linked levels
 */
class Gate extends Toggable {
    /*
     * @see pl.kedarch.mazedea.MapElement#getTermRepresentation()
     */
    @Override
    String getTermRepresentation() {
        return "G";
    }
}
