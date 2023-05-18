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
 * The Class Level 1.
 */
public class Level1 implements LevelInformation {
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
     * constructor of Level 1.
     */
    public Level1() {
        this.blocks = new ArrayList<Block>();
        Block b = new Block(new Rectangle(new Point(400 - 10, 250), 20, 20), Color.red);
        this.blocks.add(b);
        this.initialBallVelocities = new ArrayList<Velocity>();
        this.initialBallVelocities.add(new Velocity(0, -2));
        this.numberOfBalls = 1;
        this.paddleSpeed = 4;
        this.paddleWidth = 80;
        this.paddleHeight = 20;
        this.levelName = new String("Direct Hit");
        this.numberOfBlocksToRemove = 1;
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
     *  this method returns the paddle width.
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
        Sprite b = new Block(new Rectangle(new Point(0, 0), 800, 600), Color.BLACK);
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
