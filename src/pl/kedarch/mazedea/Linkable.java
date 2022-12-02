package pl.kedarch.mazedea;

import java.util.ArrayList;

/**
 * Abstract class used as base for linkable elements by attributes
 */
abstract class Linkable extends MapElement {
    /**
     * Variable holding info about link with other elements
     */
    private ArrayList<Integer> link;
    /**
     * @param link numbers that link elements
     */
    @Override
    final void setLink(ArrayList<Integer> link) {
        this.link = link;
    };
    /**
     * @return link numbers that link elements
     */
    @Override
    final ArrayList<Integer> getLink() {
        return this.link;
    };
}
