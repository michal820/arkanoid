package Animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor ks;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        ks=sensor;
        this.key=key;
        this.animation=animation;
        stop = false;
        isAlreadyPressed=true;
    }
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (ks.isPressed(key)) {
            this.stop = true;
        } else {
            this.isAlreadyPressed = false;
        }
    }
    public boolean shouldStop() {
        return stop;
    }
}
