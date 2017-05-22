import java.awt.*;//must be imported to use Graphics
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

class Background extends Panel {
    // The Image to store the background image in.
    BufferedImage bBack;
    Image back;
    public Background()
    {
        try {
            bBack = ImageIO.read(new File("arctic.jpg"));
        } 
        catch (IOException e) {
            System.out.println("Loading background image error");
        }
        //  back = Toolkit.getDefaultToolkit().createImage("background.jpg");
        back = bBack.getScaledInstance(Driver.WIDTH, Driver.HEIGHT, 0);
    }

    public void paint(Graphics g)
    {
        // Draws the img to the BackgroundPanel.
        g.drawImage(back, 0, 0, null);
    }
}