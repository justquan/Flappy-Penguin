// Fish - This class creates, processes, and controls the fish object which provides a power up
//        for the player. It also controls the methods associated with the fish.

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Fish
{
    private int xCoord;
    private int yCoord;
    public static final int DIAM = 100;
    BufferedImage bFish;
    private static Image fish;
    private boolean shouldPaint;
    public boolean doesCount;

    // Pre: None
    // Post: Processes the fish object into an image
    public Fish()
    {
        try {
            bFish = ImageIO.read(new File("power.png"));
        } 
        catch (IOException e) {
            System.out.println("Loading Fish image error");
        }
        fish = bFish.getScaledInstance(DIAM, DIAM, 0);
    }
    
    // Pre: x and y >= 0
    // Post: Sets the inital conditions of the fish object
    public Fish(int x, int y)
    {
        xCoord = x;
        yCoord = y;
        shouldPaint = true;
        doesCount = true;
    }

    // Pre: None
    // Post: Moves the fish to the right
    public void move()
    {
        xCoord -= Game.GAME_SPEED;
    }

    // Pre: page exists
    // Post: Draws the fish image if it should
    public void draw(Graphics page)
    {
        if (shouldPaint)
            page.drawImage(fish, xCoord,yCoord, null);
    }
    
    // Pre: None
    // Post: Returns the x coordinate of the fish object
    public int getX()
    {   return xCoord;  }
    
    // Pre: None
    // Post: Returns the y coordinate of the fish object
    public int getY()
    {   return yCoord;  }
    
    // Pre: None
    // Post: Sets variables to false to prevent the fish from appearing on-screen
    public void remove()
    {
        doesCount = false;
        shouldPaint = false;
    }
    
    // Pre: None
    // Post: Returns true if the fish has value, else if it doesn't
    public boolean counts()
    {
        return doesCount;
    }
}