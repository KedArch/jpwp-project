package pl.kedarch.mazedea;

/**
 * Abstract class used as base for openable elements
 */
abstract class Openable extends Linkable {
    /**
     * Variable holding info about element state
     */
    private boolean opened = false;
    /**
     * @param opened
     */
    final void setOpened(boolean opened) {
        this.opened = opened;
    };
    /**
     * @return opened
     */
    final boolean getOpened() {
        return this.opened;
    };
}
