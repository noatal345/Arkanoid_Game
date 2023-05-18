package levels;

import geometryprimitives.Point;
import geometryprimitives.Rectangle;
import geometryprimitives.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;
import sprites.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class Level 2.
 */
public class Level2 implements LevelInformation {
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
     * Constructor of Level 2.
     */
    public Level2() {
        this.blocks = new ArrayList<Block>();
        for (int i = 0; i < 15; i++) {
            Color c = new Color(10 * i, 30 / (i + 3), 100); //set different color to each line
            double h = 20;
            double w = 50;
            Point p = new Point(25 + i * w, 200);
            Block block = new Block(new Rectangle(p, w, h), c);
            blocks.add(block);
        }
        this.numberOfBalls = 10;
        this.initialBallVelocities = new ArrayList<Velocity>();
        for (int i = 0; i < this.numberOfBalls; i++) {
                this.initialBallVelocities.add(Velocity.fromAngleAndSpeed(90 + i * 20, 3));
        }
//        Velocity.fromAngleAndSpeed(90 + i * 20, 3)
        this.paddleSpeed = 4;
        this.paddleWidth = 700;
        this.paddleHeight = 20;
        this.levelName = "Wide easy";
        this.numberOfBlocksToRemove = 15;

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
        Sprite b = new Block(new Rectangle(new Point(0, 0), 800, 600), new Color(195, 170, 239));
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
