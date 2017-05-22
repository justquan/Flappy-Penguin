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
    private boolean shouldPaint;
    public boolean doesCount;

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
    
    public Coin(int x, int y)
    {
        xCoord = x;
        yCoord = y;
        shouldPaint = true;
        doesCount = true;
    }

    public void move()
    {
        xCoord -= 2;
    }

    public void draw( Graphics page )
    {
        /*
        page.setColor(Color.YELLOW);//color defined using rgb values (0-255 each)
        page.fillOval(xCoord, yCoord, DIAM, DIAM);
         *///in case the image load fails
        if (shouldPaint)
            page.drawImage(coin, xCoord,yCoord, null);
    }
    
    public int getX()
    {   return xCoord;  }
    
    public int getY()
    {   return yCoord;  }
    
    public void remove()
    {
        doesCount = false;
        shouldPaint = false;
    }
    
    public boolean counts()
    {
        return doesCount;
    }
}