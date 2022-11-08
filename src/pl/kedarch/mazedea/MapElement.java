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
     * @return imageName for GUI
     */
    abstract String getImage();
    /**
     * @return imageNameToggled for GUI
     */
    abstract String getImageToggled();
    /**
     * Default empty function
     * @param link number that links elements
     * @see pl.kedarch.mazedea.Linkable#setLink(int)
     */
    void setLink(int link) {};
    /**
     * Returns 0 by default
     * @return 0
     * @see pl.kedarch.mazedea.Linkable#getLink()
     */
    int getLink() {return 0;};
    /**
     * Default empty function
     * @see pl.kedarch.mazedea.Toggleable#toggle()
     */
    void toggle() {};
    /**
     * Returns false by default
     * @return false
     * @see pl.kedarch.mazedea.Toggleable#isToggled()
     */
    boolean isToggled() {return false;};
}
