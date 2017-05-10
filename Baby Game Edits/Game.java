import javax.swing.*; //rev 1
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class Game extends JPanel implements KeyListener
{
    private Player player;
    private Square square;
    private Block block;
    private int playerx;
    private int playery;
    private int squarex;
    private int squarey;
    private int blockx;
    private int blocky;
    private int blockTimeChange = 7000;
    private int startTime = (int)System.currentTimeMillis();
    private boolean chase = false;
    private int timer = 0;
    private int squareTimeChange = 3000;
    private JLabel label;
    
    //constructor - sets the initial conditions for this Game object
    public Game(int width, int height)
    {
        //make a panel with dimensions width by height with a black background
        this.setLayout( null );//Don't change
        this.setBackground( Color.BLACK );
        this.setPreferredSize( new Dimension( width, height ) );//Don't change
        
        //initialize the instance variables
        player = new Player( 150,150);  //change these numbers and see what happens
        square = new Square( 500, 500 ); //change these numbers and see what happens
        block = new Block();
        label = new JLabel("TIME : " + 0);
        this.add(label);
        label.setVisible(true);
        label.setBounds(10, 10, 800, 800);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 50));

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
            if(timer > blockTimeChange){
                block.changePlace();
                blockTimeChange += 7000;
            }
            playerx = player.getx();
            playery = player.gety();
            squarex = square.getx();
            squarey = square.gety();
            blockx = block.getx();
            blocky = block.gety();

             if(((((playerx + 100 >= blockx) && (playerx + 100 <= blockx + 40)) || ((playerx <= blockx + 40) && (playerx + 100 >= blockx)))  && ((playery + 100 >= blocky) && (playery <= blocky + 40))) || chase == true)
             {
                chase = true;
                square.run(playerx, playery);
                if((((playerx + 100 >= squarex) && (playerx + 100 <= squarex + 125)) || ((playerx <= squarex + 125) && (playerx + 100 >= squarex))) && ((playery + 100 >= squarey) && (playery <= squarey + 125)))
                {
                    over = true;
                    text = true;
                }
                if(timer > squareTimeChange)
                {
                    chase = false;
                    squareTimeChange += 3000;
                }
                player.move();
            }
            else{
                player.move();
                square.move(playerx, playery);
            if((((playerx + 100 >= squarex) && (playerx + 100 <= squarex + 125)) || ((playerx <= squarex + 125) && (playerx + 100 >= squarex))) && ((playery + 100 >= squarey) && (playery <= squarey + 125)))
            {
                over = true;
            }
        }
            try
            {
                Thread.sleep( 10 );//pause for 10 milliseconds
                timer += 10;
                label.setText("TIME: " + timer/1000);
            }catch( InterruptedException ex ){}
            
            this.repaint();//redraw the screen with the updated locations; calls paintComponent below
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
        super.paintComponent( page );//I'll tell you later.
        player.draw( page );//calls the draw method in the Player class
        square.draw( page );//calls the draw method in the Square class
        block.draw( page );
    }

    //not used but must be present
    public void keyReleased( KeyEvent event )
    {  
        if( event.getKeyCode() == KeyEvent.VK_LEFT ){
            player.setLeft(false);
        }
        
        if( event.getKeyCode() == KeyEvent.VK_RIGHT ){        
            player.setRight(false);
        }
        
        player.setUp(false);
        player.setDown(false);        
    }
    
    //tells the program what to do when keys are pressed
    public void keyPressed( KeyEvent event )
    {
        if( event.getKeyCode() == KeyEvent.VK_RIGHT )
        {
            player.setRight(true);
        }
        else if( event.getKeyCode() == KeyEvent.VK_LEFT )
        {
            player.setLeft(true);
        }
        else if( event.getKeyCode() == KeyEvent.VK_UP )
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
}
