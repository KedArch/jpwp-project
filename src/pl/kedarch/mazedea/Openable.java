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
     * Toggle state
     */
    final void toggle() {
        if (this.opened)
            this.opened = false;
        else
            this.opened = true;
    };
    /**
     * @return opened
     */
    final boolean isOpened() {
        return this.opened;
    };
}
