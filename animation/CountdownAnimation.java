package animation;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import general.SpriteCollection;
import interfaces.Animation;

import java.awt.Color;

/**
 * The class Countdown animation.
 * countdown from countFrom back to 1
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private int counter;
    private SpriteCollection gameScreen;

    private Sleeper sleeper;
    private boolean running;

    /**
     * constructor of Countdown animation.
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.running = true;
        this.sleeper = new Sleeper();
        this.gameScreen = gameScreen;
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.counter = countFrom + 1;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.lightGray);
        if (this.counter == 0) {
            this.running = false;
            return;
        }
        if (this.counter != (this.countFrom + 1)) {
            this.sleeper.sleepFor((long) this.numOfSeconds * 1000 / this.countFrom);
        }
        if (this.counter != 1) {
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(this.counter - 1), 60);
        } else {
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, "GO!", 60);
        }
        this.counter--;
    }
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}