import javax.swing.*; //rev 1
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Game extends JPanel implements KeyListener
{
    private Player player;
    //   private Square square;
    private int playerx;
    private int playery;
    private int startTime = (int)System.currentTimeMillis();
    public static int timer = 0;
    private JLabel scoreLabel;
    private int score;
    private ArrayList<Pipe> pipeList;
    private Background back;
    private boolean backPainted;
    private Coin loadCoin;// not sure if this is a bad way, fix
    private Sound song;

    //constructor - sets the initial conditions for this Game object
    public Game(int width, int height)
    {
        back = new Background();
        //make a panel with dimensions width by height with a black background
        this.setLayout( null );//Don't change
        this.setBackground( Color.WHITE );
        this.setPreferredSize( new Dimension( Driver.WIDTH, Driver.HEIGHT ) );//Don't change

        //initialize the instance variables
        player = new Player( (Driver.WIDTH/2)- 75/2 , (Driver.HEIGHT/2) - 75/2);  //75 is diam of player
        scoreLabel = new JLabel("Score: " + score);
        this.add(scoreLabel);
        scoreLabel.setVisible(true);
        scoreLabel.setBounds(Driver.WIDTH/2 - 200, 10, 300, 50);
        scoreLabel.setForeground(Color.RED);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 50));
        pipeList = new ArrayList<Pipe>();
        pipeList.add(new Pipe());
        score = 0;
        backPainted = false;
        this.addKeyListener(this);//allows the program to respond to key presses - Don't change

        this.setFocusable(true);//I'll tell you later - Don't change
        loadCoin = new Coin();//just to load the coin image once, not sure if this is a bad way, fix

        song = new Sound("Cyborg.wav");
    }

    //This is the method that runs the game
    public void playGame()
    {
        boolean over = false;
        boolean text = false;
        song.play();
        while( !over )
        {
            player.move();
            for (Pipe p: pipeList)
            {
                p.move();
                if(p.getCoin() != null)
                    catchCoin(player, p.getCoin());
            }

            try
            {
                Thread.sleep( 10 );//pause for 10 milliseconds
                timer += 10;
                scoreLabel.setText("Score: " + score);
            }catch( InterruptedException ex ){}

            if (timer % 2000 == 0){
                pipeList.add(new Pipe());
            }

            removePipe(); // Removes pipe if it off of the screen

            this.repaint();//redraw the screen with the updated locations; calls paintComponent below*/
        }

        if (text == true)
        {
            scoreLabel = new JLabel("GAME OVER - YOU WIN");
            /*this.add(scoreLabel);
            scoreLabel.setVisible(true);
            scoreLabel.setBounds(200, -100, 800, 800);
            scoreLabel.setForeground(Color.WHITE);
            scoreLabel.setFont(new Font("Arial", Font.BOLD, 50));       */     
        }
        else
        {
            scoreLabel = new JLabel("GAME OVER - YOU LOSE");
            /*this.add(scoreLabel);
            scoreLabel.setVisible(true);
            scoreLabel.setBounds(200, -100, 800, 800);
            scoreLabel.setForeground(Color.WHITE);
            scoreLabel.setFont(new Font("Arial", Font.BOLD, 50));*/
        }
    }

    //Precondition: executed when repaint() or paintImmediately is called
    //Postcondition: the screen has been updated with current player location
    public void paintComponent( Graphics page )
    {
        back.paint(page);
        //  super.paintComponent( page ); //I'll tell you later.???
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

    public void catchCoin(Player a, Coin c)
    {

        int coinX = c.getX();
        int coinY = c.getY();
        int playerX = a.getX();
        int playerY = (int)(a.getY());
        boolean checkX = playerX + 60 >= coinX && playerX <= coinX;
        boolean checkY1 = (playerY <= (coinY + Coin.DIAM)) && (playerY + 100 >= coinY); // Coming up
        boolean checkY2 = (playerY + 100 >= coinY) && (playerY <= coinY); // Coming down
        if (checkX && (checkY1 || checkY2))
        {
            if(c.counts())
                score++;
            c.remove();
            //  pipeList.get(pipeList.size()/2).removeCoin();//may not work if you change the game to have a different number of pipes on the screen
        }
    }

    public void removePipe(){
        if(pipeList.get(0).getX() + Pipe.PIPE_WIDTH <= 0){
            pipeList.remove(0);
        }
    }
}
