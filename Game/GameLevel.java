package Game;

import Levels.LevelInformation;
import Architecture.BallRemover;
import Architecture.BlockRemover;
import Architecture.ScoreTrackingListener;
import Sprites.*;
import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;

import biuoop.KeyboardSensor;
import physics.Velocity;
import Primitive.Counter;
import Primitive.Point;
import Primitive.Rectangle;
import physics.Collidable;
import Animation.Animation;
import Animation.AnimationRunner;
import Animation.PauseScreen;
import Animation.EndScreen;
import Animation.KeyPressStoppableAnimation;

/**
 The Game class represents a game environment where sprites and collidables are added and animated.
 It contains methods for adding collidables and sprites, initializing the game, and running the game loop.
 */
public class GameLevel implements Animation {
    private final int ballSize = 8;
    private final int rightBorder = 785;
    private final int leftBorder =  15;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private BlockRemover blockRemv;
    private BallRemover ballRemv;
    private ScoreTrackingListener scoreListener;
    private Counter blocksLeft;
    private Counter ballsLeft;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    /**
     * Adds a collidable object to the game environment.
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c)  {
        environment.addCollidable(c);
    }
    /**
     * Removes a collidable object from the game environment.
     * @param c the collidable object to remove
     */
    public void removeCollidable(Collidable c)  {
        environment.removeCollidable(c);
    }
    /**
     * Adds a sprite to the game.
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }
    /**
     * Removes a sprite from the game.
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }
    /**
     * Sets the counter for the remaining blocks.
     * @param c the counter to set
     */
    public void setBlockCounter(Counter c) {
        blocksLeft = c;
    }
    /**
     * Returns the counter for the remaining blocks.
     * @return the counter for the remaining blocks
     */
    public Counter getBlockCounter() {
      return blocksLeft;
    }
    /**
     * Sets the counter for the remaining balls.
     * @param c the counter to set
     */
    public void setBallCounter(Counter c) {
        ballsLeft = c;
    }
    /**
     * Sets the score counter.
     * @param s the score counter to set
     */
    public void setScore(Counter s) {
        score = s;
    }
    /**
     * Returns the score counter.
     * @return the score counter
     */
    public Counter getScore() {
        return  score;
    }
    /**
     * Returns the left border value.
     * @return the left border value
     */
    public int getLBorder() {
        return leftBorder;
    }
    /**
     * Returns the right border value.
     * @return the right border value
     */
    public int getRBorder() {
        return rightBorder;
    }
    public KeyboardSensor getKeyboard(){
        return keyboard;
    }
    public  AnimationRunner getAnimationRunner() {
        return runner;
    }
    private  void initializeCounters(LevelInformation info, Counter score){
        this.score = score;
        ballsLeft =new Counter();
        blocksLeft=new Counter();
        ballsLeft.setValue(info.numberOfBalls());
        blocksLeft.setValue(info.numberOfBlocksToRemove());
    }

    private void initializeSystem(LevelInformation info) {
        gui = new GUI(info.levelName(), 800, 600);
        sprites = new SpriteCollection();
        environment = new GameEnvironment();

        running =  true;
        runner = new AnimationRunner(60, gui);

        keyboard  = gui.getKeyboardSensor();

        scoreListener = new ScoreTrackingListener(score);
        scoreListener.setGame(this);

        blockRemv = new BlockRemover(this, blocksLeft);
        ballRemv = new BallRemover(this, ballsLeft);
    }
    public void initializeBorders() {
        Block borderTop = new Block(new Point(0, 30), 800, 15, Color.black);
        borderTop.addToGame(this);

        Block borderBottom = new Block(new Point(0, 585), 800, 15, Color.black);
        borderBottom.addToGame(this);
        borderBottom.addHitListener(ballRemv);

        Block borderLeft = new Block(new Point(0,  0), leftBorder, 600, Color.black);
        borderLeft.addToGame(this);

        Block borderRight = new Block(new Point(rightBorder, 0), 15, 600, Color.black);
        borderRight.addToGame(this);
    }

    public GameLevel(LevelInformation lvlInfo, Counter score) {
        initializeCounters(lvlInfo, score);
        initializeSystem(lvlInfo);
        //initialize background
        sprites.addSprite(lvlInfo.getBackground());
        initializeBorders();
        ScoreIndicator scoreIndicator = new ScoreIndicator(this, score);
        //initialize balls
        for(int i =  0; i< lvlInfo.numberOfBalls();i++) {
            Point startingLocation = lvlInfo.ballLocations().get(i);
            Color ballColor = lvlInfo.ballColors().get(i);
            Velocity v = lvlInfo.initialBallVelocities().get(i);
            Ball ball = new Ball(startingLocation, ballSize, ballColor);
            ball.setVelocity(v);
            ball.addToGame(this);
            ball.setGameEnvironment(environment);
        }
        //initialize  blocks
        for(Block b : lvlInfo.blocks()){
            b.addToGame(this);
            b.addHitListener(blockRemv);
            b.addHitListener(scoreListener);
        }

        int paddleW = 400-lvlInfo.paddleWidth()/2;
        Rectangle paddleRect =  new Rectangle(new Point(paddleW, 565), lvlInfo.paddleWidth(), 15);
        Paddle p = new Paddle(paddleRect, gui, lvlInfo.paddleSpeed());
        p.addToGame(this);
    }

    public boolean shouldStop() { return !this.running; }
    public void doOneFrame(DrawSurface d) {
        this.sprites.notifyAllTimePassed();
        this.sprites.drawAllOn(d);
        if (blocksLeft.getValue() == 0 || ballsLeft.getValue() == 0) {
            running = false;
        }
        if(ballsLeft.getValue() == 0){
            EndScreen endScreen  = new EndScreen(keyboard, score, false);
            KeyPressStoppableAnimation kpsa = new KeyPressStoppableAnimation(keyboard, keyboard.SPACE_KEY, endScreen);
            this.runner.run(kpsa);
            System.exit(0);
        }

        if (this.keyboard.isPressed("p")) {
            PauseScreen pauseScreen  = new PauseScreen();
            Animation kpsa = new KeyPressStoppableAnimation(keyboard, keyboard.SPACE_KEY, pauseScreen);
            this.runner.run(kpsa);
        }
    }
    public void run() {
        this.running = true;
//        this.runner.run( new CountdownAnimation(60, 3,sprites));
        this.runner.run(this);
    }

}
