import bagel.Image;

/**
 * The background is drawn beneath all actors and has no other actions.
 */
public class Background {

    private final String backgroundImage = "res/images/background.png";
    private final Image background;

    /**
     * Instantiates the background image object
     */
    public Background() {
        background = new Image(backgroundImage);
    }

    /**
     * Draws the background to the screen
     */
    public void draw() {
        background.drawFromTopLeft(0, 0);
    }
    

}
