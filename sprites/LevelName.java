package sprites;

import biuoop.DrawSurface;
import general.GameLevel;
import interfaces.Sprite;
import java.awt.Color;

/**
 * The type Name level.
 */
public class LevelName implements Sprite {
    private String name;

    /**
     * Constructor of a Name level.
     * @param n the name of the level
     */
    public LevelName(String n) {
        this.name = n;
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(600, 15, "Level Name :" + name, 15);
    }


    @Override
    public void timePassed() { }


    /**
     * this method adds the sprite to the game.
     * @param gameLevel the game
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
