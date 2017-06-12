// Background - This class is used to process and display the background to the game.

import java.awt.*;//must be imported to use Graphics
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class Background extends Panel 
{
    // The Image to store the background image in.
    BufferedImage bBack;
    Image back;
    
    // Pre: the file being processed is a valid photo format
    // Post: Image is created and scaled
    public Background()
    {
        try {
            bBack = ImageIO.read(new File("arctic.jpg"));
        } 
        catch (IOException e) {
            System.out.println("Loading background image error");
        }
        back = bBack.getScaledInstance(Driver.WIDTH, Driver.HEIGHT, 0); // Converts BufferedImage to Image
    }

    // Pre: g exists
    // Post: Draws the image to the BackgroundPane
    public void paint(Graphics g)
    {
        // Draws the img to the BackgroundPanel.
        g.drawImage(back, 0, 0, null);
    }
}