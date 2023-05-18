package levels;

import geometryprimitives.Rectangle;
import interfaces.LevelInformation;
import geometryprimitives.Point;
import geometryprimitives.Velocity;
import interfaces.Sprite;
import sprites.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class Level 4.
 */

public class Level4 implements LevelInformation {
    private int numberOfBalls;
    private List<Block> blocks;
    private List<Velocity> initialBallVelocities;
    private String levelName;
    private Sprite background;
    private int paddleSpeed;
    private int paddleWidth;
    private int paddleHeight;
    private int numberOfBlocksToRemove;

    /**
     * Constructor of Level 4.
     */
    public Level4() {
        this.blocks = new ArrayList<Block>();
        int lines = 7;
        int col = 15;
        for (int i = 0; i < lines; i++) {
            Color c = new Color(20 * i + 1, 80 / (i + 1), 100 + i * 20); //set different color to each line
            for (int j = 0; j < col; j++) {
                double h = 20;
                double w = 50;
                Point p = new Point(25 + (j * w), 120 + h * i);
                Block b = new Block(new Rectangle(p, w, h), c);
                blocks.add(b);
            }
        }
        this.numberOfBalls = 3;
        this.initialBallVelocities = new ArrayList<Velocity>();
        this.initialBallVelocities.add(new Velocity(2, -2));
        this.initialBallVelocities.add(new Velocity(-2, 2));
        this.initialBallVelocities.add(new Velocity(-1, 4));
        this.paddleSpeed = 4;
        this.paddleWidth = 80;
        this.paddleHeight = 20;
        this.levelName = "Final Four";
        this.numberOfBlocksToRemove = 105;
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }


    @Override
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }


    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }


    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }


    /**
     *  this method returns the paddle height.
     * @return paddle height
     */
    public int paddleHeight() {
        return this.paddleHeight;
    }


    @Override
    public String levelName() {
        return this.levelName;
    }


    @Override
    public Sprite getBackground() {
        Sprite b = new Block(new Rectangle(new Point(0, 0), 800, 600), new Color(20, 70, 150));
        return b;
    }


    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}

