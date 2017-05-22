import java.awt.*;//must be imported to use Graphics
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Player
{
    private int px;
    private double py;
    private int diam = 75;
    private boolean up = false;    
    private boolean down = false;
    public static final double g = .2;
    private double velocity;
    public static final int IMAGE_SCALE = 100;
    BufferedImage bPenguin;
    Image penguin;

    public Player( int pxLoc, int pyLoc )
    {
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

    public void move(){

        if(!up && py < 700) {
            fall();
        }

        if (up == true && py >= 0){
            py = py - 10;
            velocity = 0;
        }

    }

    public void fall(){
        velocity += g;
        py += velocity;
    }

    public void setUp(boolean press){
        up = press;
    }

    public void setDown(boolean press){
        down = press;
    }

    public int getX(){
        return px;
    }

    public double getY(){
        return py;
    }

    public void draw( Graphics page )
    {
        /*
        page.setColor( Color.BLACK );
        page.fillOval( px, (int)py, 75, 75 );//change the last two numbers and see what happens
         */ // just in case image loading fails
        page.drawImage(penguin, px,(int)py, null);
    }
}