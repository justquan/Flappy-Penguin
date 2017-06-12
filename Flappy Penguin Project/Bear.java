// Bear - This class controls the bear object which is a predator to the player. It moves upward if the
//        player is above it and if it hits the player, the player loses the game.

import java.awt.*;//must be imported to use Graphics
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Bear
{
    private int px;
    private double py;
    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;
    private boolean up = false;    
    private boolean down = false;
    public static final double g = .2;
    private double velocity;
    private int jumpSpeed;
    public static final int IMAGE_SCALE = 100;
    BufferedImage bBear;
    Image bear;

    // Pre: pyLoc and pyLoc >= 0
    public Bear( int pxLoc, int pyLoc )
    {
        px = pxLoc;
        py = pyLoc;
        velocity = -10;
        try {
            bBear = ImageIO.read(new File("realbear.png"));
        } 
        catch (IOException e) {
            System.out.println("Loading bear image error");
        }
        bear = bBear.getScaledInstance(200, 100, 0);
    }
    
    // Pre: playerX >= 0
    // Post: Moves the bear the catch up with the penguin player
    public void move(int playerX){
        px -= (Game.GAME_SPEED + 1); //To be a little faster than pipes
        if (px <= playerX + Player.WIDTH && px + WIDTH  >= playerX && py >= 0){
            py = py - 3;
            velocity = 0;
        }
        else if(py < 700) {
            fall();
        }
    }

    // Pre: None
    // Post: Changes velocity of bear to moves it faster downwards by a factor of g
    public void fall(){
        velocity += g;
        py += velocity;
    }

    // Pre: None
    // Post: Returns the x coordinate of the bear object
    public int getX(){
        return px;
    }

    // Pre: None
    // Post: Returns the y coordinate of the bear object
    public double getY(){
        return py;
    }

    // Pre: page exists
    // Post: Draws the bear image
    public void draw( Graphics page )
    {
        page.drawImage(bear, px,(int)py, null);
    }
}