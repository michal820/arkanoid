import Game.GameFlow;
import Levels.Level1;
import Levels.Level2;
import Levels.Level3;
import Levels.LevelInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * The Ass3Game class is responsible for running the game.
 * It creates an instance of the `Game` class, initializes it, and starts the game loop.
 */
public class Ass6Game {
    /**
     * The main method that starts the game.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        for(String s :  args){
            try {
                int i = Integer.parseInt(s);
                switch (i) {
                    case 1 -> levels.add(new Level1());
                    case 2 -> levels.add(new Level2());
                    case 3 -> levels.add(new Level3());
                }
            }  catch (Exception e){}
        }

//        List<LevelInformation> levels = new ArrayList<>();
//        levels.add(new Level1());
//        levels.add(new Level2());
//        levels.add(new Level3());
        GameFlow gf = new GameFlow();
        gf.runLevels(levels);
    }
}
