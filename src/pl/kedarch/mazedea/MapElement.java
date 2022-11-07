package pl.kedarch.mazedea;

/**
 * Abstract class used as base for map elements
 */
abstract class MapElement {
    /**
     * Returns element representation in terminal (CLI)
     * @return termRepresentation
     */
    abstract String getTermRepresentation();
    /**
     * Default empty function
     * @param link number that links elements
     * @see pl.kedarch.mazedea.Linkable#setLink
     */
    void setLink(int link) {};
    /**
     * Default empty function
     * @return 0
     * @see pl.kedarch.mazedea.Linkable#getLink
     */
    int getLink() {return 0;};
    /**
     * Default empty function
     * @see pl.kedarch.mazedea.Toggable#toggle
     */
    void toggle() {};
    /**
     * Default empty function
     * @return false
     * @see pl.kedarch.mazedea.Toggable#isToggled
     */
    boolean isToggled() {return false;};
}
