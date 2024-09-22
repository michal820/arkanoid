package Game;

import Animation.AnimationRunner;
import Animation.EndScreen;
import Animation.KeyPressStoppableAnimation;
import Levels.LevelInformation;
import Primitive.Counter;
import biuoop.KeyboardSensor;

import java.util.List;

public class GameFlow {
    private AnimationRunner ar;
    private KeyboardSensor  ks;
    public GameFlow() {

    }
    public void runLevels(List<LevelInformation> levels) {
        Counter score  = new Counter();
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, score);
            level.run();
            score  = level.getScore();
            ar = level.getAnimationRunner();
            ks = level.getKeyboard();
        }
        EndScreen endScreen  = new EndScreen(ks, score, true);
        KeyPressStoppableAnimation kpsa = new KeyPressStoppableAnimation(ks, ks.SPACE_KEY, endScreen);
        ar.run(kpsa);
        System.exit(0);
    }
}
