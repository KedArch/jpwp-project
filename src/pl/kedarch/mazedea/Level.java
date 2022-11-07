package pl.kedarch.mazedea;

/**
 * Element toggling gates with the same link
 */
class Level extends Linkable {
    /**
     * Variable storing info about class state
     */
    private boolean toggled;

    /**
     * @return toggled
     */
    boolean isToggled() {
        return toggled;
    }

    /**
     * Toggle state
     */
    void toggle() {
        if (this.toggled)
            this.toggled = false;
        else
            this.toggled = true;
    }
}
