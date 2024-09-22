package Levels;

import Levels.Backgrounds.LevelOneBackground;
import Levels.Backgrounds.LevelThreeBackground;
import physics.Velocity;
import Primitive.Point;
import Primitive.Rectangle;
import Sprites.Block;
import Sprites.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Level3 implements LevelInformation{
    final int NUM_OF_BALLS = 3;
    final int PADDLE_SPEED = 25;
    final int PADDLE_WIDTH = 100;
    final String LEVEL_NAME = "Level Three";
    final int  NUM_OF_BLOCKS=50;
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(-5,2));
        velocities.add(new Velocity(-7,-2));
        velocities.add(new Velocity(-2,6));
        return velocities;
    }
    public List<Color> ballColors() {
        List<Color> c = new ArrayList<>();
        c.add(Color.blue);
        c.add(Color.cyan);
        c.add(Color.gray);
        return c;
    }
    public List<Point> ballLocations() {
        List<Point>  p = new ArrayList<>();
        p.add(new  Point(380,400));
        p.add(new  Point(400,400));
        p.add(new  Point(420,400));
        return p;
    }
    public int paddleSpeed(){
        return PADDLE_SPEED;
    }
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    public String levelName (){
        return LEVEL_NAME;
    }
    public Sprite getBackground(){
        return new LevelThreeBackground();
    }
    // The Blocks that make up this level, each block contains
    // its size, color and location.
    public List<Block> blocks(){
        List<Block> blocks = new ArrayList<>();
        int h = 50;
        for (int i = 0; i < 12; i++) {
            Block b = new Block(new Point(185 + h * i, 120), 50, 30, Color.gray);
            blocks.add(b);
        }
        for (int i = 0; i < 11; i++) {
            Block b = new Block(new Point(235 + h * i, 150), 50, 30, Color.red);
            blocks.add(b);
        }
        for (int i = 0; i < 10; i++) {
            Block b = new Block(new Point(285 + h * i, 180), 50, 30, Color.pink);
            blocks.add(b);
        }
        for (int i = 0; i < 9; i++) {
            Block b = new Block(new Point(335 + h * i, 210), 50, 30, Color.orange);
            blocks.add(b);
        }
        for (int i = 0; i < 8; i++) {
            Block b = new Block(new Point(385 + h * i, 240), 50, 30, Color.yellow);
            blocks.add(b);
        }
        return blocks;
    }
    public int numberOfBlocksToRemove(){
        return NUM_OF_BLOCKS;
    }
}
