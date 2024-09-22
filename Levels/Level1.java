package Levels;

import Levels.Backgrounds.LevelOneBackground;
import physics.Velocity;
import Primitive.Point;
import Primitive.Rectangle;
import Sprites.Block;
import Sprites.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Level1 implements LevelInformation{
    final int NUM_OF_BALLS = 1;
    // 0 6
    final List<Velocity> BALL_VELOCITIES = Arrays.asList(new Velocity(0,6));
    final int PADDLE_SPEED = 10;
    final int PADDLE_WIDTH = 200;
    final String LEVEL_NAME = "Level One";
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }
    public List<Velocity> initialBallVelocities() {
        return BALL_VELOCITIES;
    }
    public List<Color> ballColors() {
       List<Color> c = new ArrayList<>();
       c.add(Color.green);
       return c;
    }
    public List<Point> ballLocations() {
        List<Point>  p = new ArrayList<>();
        p.add(new  Point(400,400));
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
        return new LevelOneBackground();
    }
    // The Blocks that make up this level, each block contains
    // its size, color and location.
    public List<Block> blocks(){
        List<Block> blocks = new ArrayList<>();
        Block b = new Block(new Rectangle(new Point(385,200), 30,  30), Color.green);
        blocks.add(b);
        return blocks;
    }
   public int numberOfBlocksToRemove(){
        return  1;
    }
}
