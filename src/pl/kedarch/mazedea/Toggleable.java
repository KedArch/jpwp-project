package pl.kedarch.mazedea;

/**
 * Abstract class used as base for openable elements
 */
abstract class Toggleable extends Linkable {
    /**
     * Variable holding info about element state
     */
    private boolean toggled = false;
    /**
     * Toggle state
     */
    @Override
    final void toggle() {
        if (this.toggled)
            this.toggled = false;
        else
            this.toggled = true;
    };
    /**
     * @return toggled state
     */
    @Override
    final boolean isToggled() {
        return this.toggled;
    };
}
