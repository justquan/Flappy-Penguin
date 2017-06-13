// Pipe - This class creates the pipe object which consists of two triganles which point to each other.
//        It also contains and controls necessary methods attributed to the pipe object.

import java.awt.*;//must be imported to use Graphics
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Pipe
{
    private int xCoord;
    private int yCoord;
    private Coin myCoin;
    public static final int GAP = 250;
    public static final int PIPE_WIDTH = 125;
    public static final int CLEARANCE = 100;
    // The following private variables are used for polygon drawing
    private int [] xPoints;
    private int [] yPointsTop;
    private int [] yPointsBottom;
    private int nPoints;
    private Polygon top;
    private Polygon bottom;

    // Pre: None
    // Post: Sets the inital conditions of the pipe by creating two triangles and putting a coin in the pipe
    public Pipe()
    {
        xCoord = Driver.WIDTH - PIPE_WIDTH; // Top left corner of the pipe object
        yCoord = 0; // Top left corner of the pipe object

        int topHeight = (int) ((Math.random() * (Driver.HEIGHT - GAP - CLEARANCE))) + CLEARANCE;
        int bottomHeight = Driver.HEIGHT - GAP - topHeight - yCoord;

        xPoints = new int [] {xCoord, xCoord + PIPE_WIDTH, (2*xCoord + PIPE_WIDTH)/2};
        yPointsTop = new int [] {yCoord, yCoord, yCoord + topHeight};
        yPointsBottom = new int [] {Driver.HEIGHT, Driver.HEIGHT, Driver.HEIGHT - bottomHeight};
        nPoints = 3; // Need 3 points to draw a triangle
        
        top = new Polygon(xPoints, yPointsTop, nPoints);
        bottom = new Polygon(xPoints, yPointsBottom, nPoints);

        int coinX = (2*xCoord + PIPE_WIDTH)/2 - Coin.DIAM/2;
        int coinY = topHeight + (GAP/2) - Coin.DIAM/2;
        myCoin = new Coin(coinX, coinY);
    } 

    // Pre: None
    // Post: Moves the pipe to the left and adjusts variables 
    public void move()
    {
        xCoord -= Game.GAME_SPEED;
        for (int i = 0; i < xPoints.length; i++)
            xPoints[i] -= Game.GAME_SPEED;
        if (myCoin != null)
            myCoin.move();
        top = new Polygon(xPoints, yPointsTop, nPoints); // Need to redefine up and bottom since pipe moves
        bottom = new Polygon(xPoints, yPointsBottom, nPoints);
    }

    // Pre: page exists
    // Post: Draws the pipe and coin
    public void draw( Graphics page )
    {
        page.setColor(Color.WHITE);
        page.fillPolygon(top);
        page.fillPolygon(bottom);
        myCoin.draw(page);
    }

    // Pre: None
    // Post: Returns the x coordinate of the pipe object
    public int getX(){
        return xCoord;
    }

    // Pre: None
    // Post: Returns the y coordinate of the pipe object
    public int getY(){
        return yCoord;
    }

    // Pre: None
    // Post: Returns myCoin
    public Coin getCoin()
    {
        return myCoin;
    }

    // Pre: x and y >= 0
    // Post: Returns true if x and y are coordinates inside the pipe, false if not
    public boolean inside(int x, int y)
    {
        if (top.contains(x, y) || bottom.contains(x,y))
            return true;
        else 
            return false;
    }
}