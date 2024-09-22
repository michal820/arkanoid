package Animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import Game.SpriteCollection;

import java.awt.*;

public class CountdownAnimation implements Animation {
    double numOfSeconds;
    int countFrom;
    SpriteCollection gameScreen;
    private boolean stop;

    int counter;
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        counter= countFrom+1;
        stop=false;
    }
//    public void doOneFrame(DrawSurface d) {
//        gameScreen.drawAllOn(d);
//        Sleeper sleeper = new Sleeper();
//        d.setColor(Color.orange);
//        if (countFrom == 0) {
//            sleeper.sleepFor((long) (1000*2));
//            d.drawText(400, 300, "GO", 32);
//            sleeper.sleepFor((long) (1000*2));
//            stop=true;
//        }
//        if(countFrom!=0) {
//            sleeper.sleepFor((long) (1000*2));
//            d.drawText(400, 300, String.valueOf(countFrom), 50);
//            countFrom--;
//        }
//    }
public void doOneFrame(DrawSurface d) {
    Sleeper sleeper = new Sleeper();
    this.gameScreen.drawAllOn(d);
    counter--;
    //setting the countdown
    if (counter > 0) {
        d.setColor(Color.ORANGE);
        d.drawText(380, 200, Integer.toString(counter), 50);
        if (counter < numOfSeconds) {
            sleeper.sleepFor((countFrom * 1000) / (long) numOfSeconds);
        }
    } else  if (counter > -2) { //setting the "GO"
        d.setColor(Color.RED);
        d.drawText(380, 200, "GO", 50);
        sleeper.sleepFor((countFrom * 1000) / (long) numOfSeconds);
    } else {
        this.stop = true;
    }
}
    public boolean shouldStop() {
        return this.stop;
    }
}
