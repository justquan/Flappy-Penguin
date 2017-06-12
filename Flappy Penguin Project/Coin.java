// Coin - This class controls a coin object which counts for points if collected. They are present between the pipes
//        and count for 1 point added to the score. This class controls the methods associated with a coin.

import java.awt.*;//must be imported to use Graphics
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Coin
{
    private int xCoord;
    private int yCoord;
    public static final int DIAM = 75;
    BufferedImage bCoin;
    private static Image coin;
    public boolean doesCount;
    private boolean shouldPaint;
    
    // Pre: None
    // Post: Renders a coin image
    public Coin()
    {
        try {
            bCoin = ImageIO.read(new File("coin.png"));
        } 
        catch (IOException e) {
            System.out.println("Loading coin image error");
        }
        coin = bCoin.getScaledInstance(DIAM, DIAM, 0);
    }
    
    // Pre: x and y >= 0
    // Post: Sets initial conditions of a coin
    public Coin(int x, int y)
    {
        xCoord = x;
        yCoord = y;
        doesCount = true;
        shouldPaint = true;
    }

    // Pre: None
    // Post: Moves the coin to the left of the screen
    public void move()
    {
        xCoord -= Game.GAME_SPEED;
    }

    // Pre: page exists
    // Post: Paints the coin image if it should
    public void draw( Graphics page )
    {
        if (shouldPaint)
            page.drawImage(coin, xCoord, yCoord, null);
    }
    
    // Pre: None
    // Post: Returns the x coordinate of the coin object
    public int getX()
    {   return xCoord;  }
    
    // Pre: None
    // Post: Returns the y coordinate of the coin object
    public int getY()
    {   return yCoord;  }
    
    // Pre: None
    // Post: Changes private variables to reflect a non-appearing and worthless coin object
    public void remove()
    {
        doesCount = false;
        shouldPaint = false;
    }
    
    // Pre: None
    // Post: Returns if the coin counts for value
    public boolean counts()
    { return doesCount; }
}