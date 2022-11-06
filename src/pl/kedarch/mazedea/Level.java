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
     * @return
     */
    boolean isToggled() {
        return toggled;
    }

    /**
     * @param toggled
     */
    void setToggled(boolean toggled) {
        this.toggled = toggled;
    }
}
