import javax.swing.*; //rev 1
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Game extends JPanel implements KeyListener
{
    private Player player;
    //   private Square square;
    private int playerx;
    private int playery;
    private int startTime = (int)System.currentTimeMillis();
    public static int timer = 0;
    private JLabel label;
    private ArrayList<Pipe> pipeList;

    //constructor - sets the initial conditions for this Game object
    public Game(int width, int height)
    {
        //make a panel with dimensions width by height with a black background
        this.setLayout( null );//Don't change
        this.setBackground( Color.WHITE );
        this.setPreferredSize( new Dimension( Driver.WIDTH, Driver.HEIGHT ) );//Don't change

        //initialize the instance variables
        player = new Player( (Driver.WIDTH /2)- 75/2 ,Driver.HEIGHT/2 - 75/2);  //75 is diam of player
        //    square = new Square( 500, 500 ); //change these numbers and see what happens
        label = new JLabel("TIME : " + 0);
        this.add(label);
        label.setVisible(true);
        label.setBounds(10, 10, 800, 800);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        pipeList = new ArrayList<Pipe>();
        pipeList.add(new Pipe());
        this.addKeyListener(this);//allows the program to respond to key presses - Don't change

        this.setFocusable(true);//I'll tell you later - Don't change
    }

    //This is the method that runs the game
    public void playGame()
    {
        boolean over = false;
        boolean text = false;
        while( !over )
        {
            player.move();
            for (Pipe p: pipeList)
            {
                p.move();
            }
            
            try
            {
                Thread.sleep( 10 );//pause for 10 milliseconds
                timer += 10;
                label.setText("TIME: " + timer/1000);
            }catch( InterruptedException ex ){}

            if (timer % 2000 == 0){
                pipeList.add(new Pipe());
            }
            removePipe(); // Removes pipe if it off of the screen

            this.repaint();//redraw the screen with the updated locations; calls paintComponent below*/
        }

        if (text == true)
        {
            label = new JLabel("GAME OVER - YOU WIN");
            this.add(label);
            label.setVisible(true);
            label.setBounds(200, -100, 800, 800);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, 50));            
        }
        else
        {
            label = new JLabel("GAME OVER - YOU LOSE");
            this.add(label);
            label.setVisible(true);
            label.setBounds(200, -100, 800, 800);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.BOLD, 50));
        }
    }

    //Precondition: executed when repaint() or paintImmediately is called
    //Postcondition: the screen has been updated with current player location
    public void paintComponent( Graphics page )
    {
        super.paintComponent( page ); //I'll tell you later.
        player.draw( page ); //calls the draw method in the Player class
        for (Pipe p: pipeList)
            p.draw(page);
    }

    //not used but must be present
    public void keyReleased( KeyEvent event )
    {  
        player.setUp(false);
        player.setDown(false);        
    }

    //tells the program what to do when keys are pressed
    public void keyPressed( KeyEvent event )
    {
        if( event.getKeyCode() == KeyEvent.VK_UP )
        {
            player.setUp(true);
        }
        else if( event.getKeyCode() == KeyEvent.VK_DOWN )
        {
            player.setDown(true);
        }
    }

    //not used but must be present
    public void keyTyped( KeyEvent event )
    {
    }

    public void removePipe(){
        if(pipeList.get(0).getX() + Pipe.PIPE_WIDTH <= 0){
            pipeList.remove(0);
        }
    }
}
