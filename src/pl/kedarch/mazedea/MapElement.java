package pl.kedarch.mazedea;

import java.util.ArrayList;

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
     * @param link numbers that link elements
     * @see pl.kedarch.mazedea.Linkable#setLink(int)
     */
    void setLink(ArrayList<Integer> link) {};
    /**
     * Returns null by default
     * @return null
     * @see pl.kedarch.mazedea.Linkable#getLink()
     */
    ArrayList<Integer> getLink() {return null;};
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
