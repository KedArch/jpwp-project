package pl.kedarch.mazedea;

/**
 * Abstract class used as base for linkable elements by attributes
 */
abstract class Linkable extends MapElement {
    /**
     * Variable holding info about link with other elements
     */
    private int link;
    /**
     * @param link
     */
    final void setLink(int link) {
        this.link = link;
    };
    /**
     * @return link
     */
    final int getLink() {
        return this.link;
    };
}
