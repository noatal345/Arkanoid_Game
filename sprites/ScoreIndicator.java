package sprites;

import biuoop.DrawSurface;
import geometryprimitives.Rectangle;
import interfaces.Sprite;
import general.Counter;
import general.GameLevel;

import java.awt.Color;

/**
 * Class Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Rectangle scoreRec;
    private Counter score;

    /**
     * constructor Score tracking listener from a rectangle and a score.
     * @param r     the rectangle
     * @param score the score
     */
    public ScoreIndicator(Rectangle r, Counter score) {
        this.scoreRec = r;
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.BLACK);
        d.drawText((int) (this.scoreRec.getUpperLeft().getX() + this.scoreRec.getWidth() / 2), 15,
                "score " + Integer.toString(this.score.getValue()), 15);
    }

    @Override
    public void timePassed() { }

    /**
     * this method add the score indicator to the game.
     * @param gameLevel the game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
