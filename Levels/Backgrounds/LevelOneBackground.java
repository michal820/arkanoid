package Levels.Backgrounds;

import biuoop.DrawSurface;
import Sprites.Sprite;

import java.awt.*;

public class LevelOneBackground implements Sprite {

    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0,30,800,600);
        d.setColor(Color.red);
        d.fillCircle(400,220,100);
        d.setColor(Color.white);
        d.fillCircle(400,220,80);
        d.setColor(Color.red);
        d.fillCircle(400,220,70);
        d.setColor(Color.white);
        d.fillCircle(400,220,60);
        d.setColor(Color.red);
        d.fillCircle(400,220,50);
        d.setColor(Color.white);
        d.fillCircle(400,220,40);
    }
    public void timePassed() {

    }
}
