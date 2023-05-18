package general;

import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.KeyboardSensor;
import geometryprimitives.Point;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import geometryprimitives.Rectangle;
import geometryprimitives.Velocity;
import hitlisteners.BallRemover;
import hitlisteners.BlockRemover;
import hitlisteners.ScoreTrackingListener;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import sprites.Ball;
import sprites.Block;
import sprites.LevelName;
import sprites.Paddle;
import sprites.ScoreIndicator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * public class general.Game.
 * This class holds the sprites, the collidables, and in charge of the animation.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private int height = 600;
    private int width = 800;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private List<Block> blocks;
    private List<Velocity> initialBallVelocities;
    private Paddle paddle;
    private LevelInformation level;

    /**
     * Constructor general.Game holds lists of sprites and collidables.
     * @param level the level.
     * @param animationRunner the animation runner
     * @param keyboard the keyboard Sensor
     * @param gui the gui
     * @param score the score
     */
    public GameLevel(LevelInformation level, AnimationRunner animationRunner, KeyboardSensor keyboard, GUI gui,
                     Counter score) {
        this.level = level;
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.height = 600;
        this.width = 800;
        this.remainingBlocks = new Counter(this.level.numberOfBlocksToRemove());
        this.remainingBalls = new Counter(0);
        this.score = score;
        this.sleeper = new Sleeper();
        this.keyboard = keyboard;
        this.runner = animationRunner;
        this.blocks = new ArrayList<Block>();
        this.paddle = new Paddle(new Rectangle(new Point(this.width / 2 - this.level.paddleWidth() / 2,
                this.height - 20 - this.level.paddleHeight()),
                this.level.paddleWidth(), this.level.paddleHeight()), Color.ORANGE, keyboard, level.paddleSpeed());
        this.gui = gui;
    }


    /**
     * This method adds a interfaces.Collidable object to the environment.
     * @param c is the interfaces.Collidable object
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * This method adds a interfaces.Sprite to the sprite collection.
     *
     * @param s is the interfaces.Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * this method remove a collidable from the collidables.
     * @param c the collidable object
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * this method remove a sprite from the sprite collection.
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * this method sets the bounds.
     */
    public void setBounds() {
        Block l1 = new Block(new Rectangle(new Point(0, 20), this.width, 20), Color.DARK_GRAY); //up
        Block l2 = new Block(new Rectangle(new Point(0, 20), 25, this.height), Color.DARK_GRAY); //left
        Block l3 = new Block(new Rectangle(new Point(this.width - 25, 20), 25, this.height), //right
                Color.DARK_GRAY);

        //add to both sprites and collidables
        l1.addToGame(this);
        l2.addToGame(this);
        l3.addToGame(this);

        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        //add a ball remover to the death-region.
        Block l4 = new Block(new Rectangle(new Point(0, this.height), this.width, 20), Color.DARK_GRAY); //down
        l4.addHitListener(ballRemover);
        l4.addToGame(this);
    }

    /**
     * this method sets the blocks.
     */
    public void setBlocks() {
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        for (int i = 0; i < this.level.blocks().size(); i++) {
            Block newBlock = this.level.blocks().get(i);
            newBlock.addToGame(this); //add to both sprites and collidables
            newBlock.addHitListener(blockRemover);
            newBlock.addHitListener(scoreTrackingListener);
        }
    }

    /**
     * this method sets the paddle.
     */
    public void setPaddle() {
        this.paddle.addToGame(this);
    }

    /**
     * this method sets the balls.
     */
    public void setBalls() {
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            Ball b = new Ball(new Point((this.width / 2), this.height - 50), 4, Color.WHITE, this.environment);
            b.setVelocity(this.level.initialBallVelocities().get(i));
            this.remainingBalls.increase(1);
            b.addToGame(this);
        }
    }

    /**
     * this method sets the background.
     */
    public void setBackground() {
        addSprite(this.level.getBackground());
    }

    /**
     * This method initializes a new game:
     * creates the Blocks, sprites.Ball, and sprites.Paddle. and add them to the game.
     */
    public void initialize() {
        this.setBackground();
        ScoreIndicator sI = new ScoreIndicator(new Rectangle(new Point(0, 0), 800, 15), this.score);
        sI.addToGame(this);
        LevelName name = new LevelName(this.level.levelName());
        name.addToGame(this);
        this.setPaddle();
        this.setBounds();
        this.setBalls();
        this.setBlocks();
    }

    /**
     * this method return the number of blocks left.
     * @return blockCounter
     */
    public Counter getBlockCounter() {
        return this.remainingBlocks;
    }

    /**
     * this method return the number of blocks left.
     * @return blockCounter
     */
    public Counter getBallCounter() {
        return this.remainingBalls;
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(); //move the sprites
        // stopping condition
        if (this.getBallCounter().getValue() == 0) {
            this.running = false;
        }
        if (this.getBlockCounter().getValue() == 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation((this.keyboard), "space",
                    new PauseScreen(this.keyboard)));
        }
    }

    /**
     * This method run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }
}
