package pl.kedarch.mazedea;

/**
 * Abstract class used as base for map elements
 */
abstract class MapElement {
    /**
     * Default empty function
     * @param link
     * @see pl.kedarch.mazedea.Linkable.setLink
     */
    void setLink(int link) {};
    /**
     * Default empty function
     * @return 0
     * @see pl.kedarch.mazedea.Linkable.getLink
     */
    int getLink() {return 0;};
    /**
     * Default empty function
     */
    void toggle() {};
    /**
     * Default empty function
     * @return false
     * @see pl.kedarch.mazedea.Openable.toggle
     * @see pl.kedarch.mazedea.Level.toggle
     */
    boolean isOpened() {return false;};
    /**
     * Default empty function
     * @return false
     * @see pl.kedarch.mazedea.Openable.isOpened
     */
    boolean isToggled() {return false;};
}
