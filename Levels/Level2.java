package Levels;

import Levels.Backgrounds.LevelOneBackground;
import Levels.Backgrounds.LevelTwoBackground;
import physics.Velocity;
import Primitive.Point;
import Primitive.Rectangle;
import Sprites.Block;
import Sprites.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Level2 implements LevelInformation{
    final int GUIWIDTH = 800;
    final int GUIHEIGHT = 600;
    final int BORDERWIDTH = 15;
    final int NUM_OF_BALLS = 10;
    final int PADDLE_SPEED = 10;
    final int PADDLE_WIDTH = 600;
    final String LEVEL_NAME = "Level Two";
    public int numberOfBalls() {
        return NUM_OF_BALLS;
    }
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < numberOfBalls()/2; i++) {
            //5 -5
            Velocity v = new Velocity(5 , -5);
            velocities.add(v);
            //-5 -5
            v = new Velocity(-5,-5);
            velocities.add(v);
        }
        return velocities;
    }
    public List<Color> ballColors() {
        List<Color> c = new ArrayList<>();
        for (int i =0; i<NUM_OF_BALLS; i++){
            c.add(Color.blue);
        }
        return c;
    }
    public List<Point> ballLocations() {
        List<Point> locations =  new ArrayList<>();
        int xCenter = GUIWIDTH/2;
        for (int i = 0; i < NUM_OF_BALLS / 2; i++) {
            Point p = new Point(xCenter + 40 + i*50, 350 + i*i*5 + 20);
            locations.add(p);
            p = new Point(xCenter - 40 - i*50, 350 + i*i*5 + 20);
            locations.add(p);
        }
        return locations;
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
        return new LevelTwoBackground();
    }
    // The Blocks that make up this level, each block contains
    // its size, color and location.
    private void setBlockColors(List<Block>blocks) {
        blocks.get(0).changeColor(Color.pink);
        blocks.get(1).changeColor(Color.pink);
        blocks.get(2).changeColor(Color.red);
        blocks.get(3).changeColor(Color.red);
        blocks.get(4).changeColor(Color.orange);
        blocks.get(5).changeColor(Color.orange);
        blocks.get(6).changeColor(Color.yellow);
        blocks.get(7).changeColor(Color.yellow);
        blocks.get(8).changeColor(Color.green);
        blocks.get(9).changeColor(Color.green);
        blocks.get(10).changeColor(Color.green);
        blocks.get(11).changeColor(Color.cyan);
        blocks.get(12).changeColor(Color.cyan);
        blocks.get(13).changeColor(Color.blue);
        blocks.get(14).changeColor(Color.blue);
    }
    public List<Block> blocks(){
        List<Block> blocks = new ArrayList<>();
        int blockWidth = (GUIWIDTH - 2*BORDERWIDTH)/15;
        for(int i = 0; i<15;i++) {
            Point upperLeft = new Point(blockWidth*(i) + BORDERWIDTH+2, 200);
            Block b = new Block(new Rectangle(upperLeft, blockWidth,20), Color.black);
            blocks.add(b);
        }
        setBlockColors(blocks);
        return blocks;
    }
    public int numberOfBlocksToRemove(){
        return  15;
    }
}
