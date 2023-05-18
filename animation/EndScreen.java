package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * Class EndScreen.
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private int score;
    private boolean status;

    /**
     * Constructor of End screen.
     * @param k the keyboard sensor
     * @param score the final score
     * @param s the status winner or loser
     */
    public EndScreen(KeyboardSensor k, int score, Boolean s) {
        this.keyboard = k;
        this.stop = false;
        this.score = score;
        this.status = s;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.status) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is: " + this.score, 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over! Your score is: " + this.score, 32);
        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
