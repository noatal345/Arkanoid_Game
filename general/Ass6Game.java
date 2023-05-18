package general;

import animation.AnimationRunner;
import biuoop.GUI;
import interfaces.LevelInformation;
import levels.GameFlow;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.Level4;

import java.util.ArrayList;
import java.util.List;


/** public class Ass6Game.
 * This class starts a new game
 */
public class Ass6Game {
    /**
     *  This is the main method.
     * @param args is not used in this project
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);

        List<LevelInformation> levelsToPLay = new ArrayList<LevelInformation>();
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                char c = args[i].charAt(0); //the first char of the string
                int level = c - '0';
                switch (level) {
                    case 1:
                        levelsToPLay.add(new Level1());
                        break;
                    case 2:
                        levelsToPLay.add(new Level2());
                        break;
                    case 3:
                        levelsToPLay.add(new Level3());
                        break;
                    case 4:
                        levelsToPLay.add(new Level4());
                        break;
                    default:
                        break;
                }
            }
        }
        if (levelsToPLay.isEmpty()) { //in order
            levelsToPLay.add(new Level1());
            levelsToPLay.add(new Level2());
            levelsToPLay.add(new Level3());
            levelsToPLay.add(new Level4());
        }
        GameFlow game = new GameFlow(animationRunner, gui.getKeyboardSensor(), gui);
        game.runLevels(levelsToPLay);
    }
}

