package levels;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import general.Counter;
import general.GameLevel;
import interfaces.LevelInformation;

import java.util.List;

/**
 * The Class Game flow.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter score;
    private GUI gui;
    private boolean status;

    /**
     * Constructor of Game flow.
     * @param ar the animationRunner
     * @param ks the keyboardSensor
     * @param gui the gui
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.score = new Counter(0);
        this.gui = gui;
        this.status = true;
    }

    /**
     * this method runs the levels.
     * @param levels the levels ro run
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.animationRunner, this.keyboardSensor, gui, this.score);
            level.initialize();
            level.run();
            if (level.getBlockCounter().getValue() == 0) {
                this.score.increase(100);
            }
            if (level.getBallCounter().getValue() == 0) {
                this.status = false;
                break;
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(
                this.keyboardSensor, "space", new EndScreen(this.keyboardSensor, this.score.getValue(), this.status)));
        System.exit(0);
    }
}