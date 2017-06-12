// Justin Quan
// Driver - This class is used to create the JFrame and calls necessary methods to run the game.

import javax.swing.*;
import java.awt.*;
public class Driver
{
    public static final int WIDTH = 1700;
    public static final int HEIGHT = 900;
    
    // Pre: None
    // Post: Creates the JFrame and calls necessary methods to run the game
    public static void driver()
    {
        //create a JFrame (window) that will be visible on screen
        JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Makes the red close button work
        frame.setLocation(0, 0); // Places the frame in the upper left corner
        Game game = new Game(WIDTH, HEIGHT); // Instantiates the game
        frame.getContentPane().add(game); // Add game to the frame so it will be on the screen
        frame.pack();
        frame.setVisible(true);
        game.playGame(); // Runs the game
    }
}