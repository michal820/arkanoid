package Levels.Backgrounds;
import Sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.*;

public class LevelTwoBackground implements Sprite{
    public void drawOn(DrawSurface d) {
        d.setColor(Color.decode("#89D1E6"));
        d.fillRectangle(0,30,800,600);
        d.setColor(Color.white);
        d.fillCircle(300,150,50);
        d.fillCircle(350,160,30);
        d.fillCircle(250,160,30);

        d.fillCircle(600,250,100);
        d.fillCircle(700,260,70);
        d.fillCircle(500,260,70);

        d.fillCircle(200,350,70);
        d.fillCircle(300,360,50);
        d.fillCircle(100,360,50);

        d.setColor(Color.orange);
        d.fillCircle(60,80,80);
        d.setColor(Color.yellow);
        d.fillCircle(60,80,50);

    }

    public void timePassed() {

    }
}
