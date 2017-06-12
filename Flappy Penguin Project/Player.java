// Player - This class creates, processes, and controls the penguin object and the methods associated with its movement.

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Player
{
    private int px;
    private double py;
    public static final int WIDTH = 45;
    public static final int HEIGHT = 90;
    private boolean up = false;    
    private boolean down = false;
    public static final double g = .2;
    private double velocity;
    public static final int DIAM = 75;
    private BufferedImage bPenguin;
    private Image penguin;
    private boolean powered;

    // Pre: pxLoc and pyLoc >= 0
    // Post: Processes the penguin image and sets intial conditions
    public Player( int pxLoc, int pyLoc )
    {
        powered = false;
        px = pxLoc;
        py = pyLoc;
        velocity = 0.0;
        try {
            bPenguin = ImageIO.read(new File("penguin.png"));
        } 
        catch (IOException e) {
            System.out.println("Loading penguin image error");
        }
        penguin = bPenguin.getScaledInstance(100, 100, 0);
    }

    // Pre: None
    // Post: Enlarges the penguin image
    public void powerUp(){
        powered = true;
        penguin = bPenguin.getScaledInstance(600, 600, 0);    
    }

    // Pre: None
    // Post: Makes the penguin image smaller
    public void powerDown(){
        powered = false;
        penguin = bPenguin.getScaledInstance(100, 100, 0);
    }
    
    // Pre: None
    // Post: Returns true if penguin is powered up, false if it isn't
    public boolean powered() 
    {
        return powered;
    }

    // Pre: None
    // Post: Controls the y motion of the player based on its position and information passed to the method
    public void move()
    {
        if(!up && !powered && py < Driver.HEIGHT - HEIGHT) 
            fall();
        else if(!up && powered && py < Driver.HEIGHT - 500)
            fall();

        if (up == true && py >= 0){
            py = py - 8;
            velocity = 0;
        }
    }

    // Pre: None
    // Post: Increases downward velocity of player by increasing it velocity by factor g
    public void fall()
    {
        velocity += g;
        py += velocity;
    }

    // Pre: None
    // Post: up is set to press
    public void setUp(boolean press)
    {
        up = press;
    }

    // Pre: None
    // Post: down is set to pree
    public void setDown(boolean press)
    {
        down = press;
    }

    // Pre: None
    // Post: Returns player x coordinate
    public int getX()
    {
        return px;
    }

    // Pre: None
    // Post: Returns player y coordinate
    public double getY()
    {
        return py;
    }

    // Pre: page exists
    // Post: Draws the penguin image
    public void draw(Graphics page)
    {
        page.drawImage(penguin, px,(int)py, null);
    }
}