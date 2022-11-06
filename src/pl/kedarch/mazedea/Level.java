package pl.kedarch.mazedea;

/**
 * Element toggling gates with the same link
 */
class Level {
    /**
     * Variable storing info about class state
     */
    private boolean toggled;

    /**
     * @return
     */
    public boolean isToggled() {
        return toggled;
    }

    /**
     * @param toggled
     */
    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }
}
