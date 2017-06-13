// Game - This class sets up the overall aspects of the game and uses all of the other classes
//        to put together a working game. Some of the things it does is play the game, paints the screen,
//        sets up initial conditions, and checks for collisions.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Game extends JPanel implements KeyListener
{
    private Player player;
    public static final int GAME_SPEED = 2; // How fast the screen moves
    private int startTime;
    public static int timer = 0;
    private JLabel scoreLabel;
    private JLabel quote;
    private int score;
    private Background back;
    private Sound song;
    private Sound bling;
    private ArrayList<Pipe> pipeList;
    private ArrayList<Bear> bearList;
    private ArrayList<Fish> fishList;
    private Coin loadCoin;
    private Fish loadFish;

    // Pre: width and height are greater than 0
    // Post: Sets the inital conditions of the game and screen
    public Game(int width, int height)
    {

        // Making the panel, background, and key listener
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(Driver.WIDTH, Driver.HEIGHT));
        this.addKeyListener(this);
        this.setFocusable(true);
        back = new Background();

        // Placing items in the game
        player = new Player((Driver.WIDTH/2) - Player.DIAM/2, (Driver.HEIGHT/2) - Player.DIAM/2);
        scoreLabel = new JLabel("Score: " + score, SwingConstants.CENTER);
        this.add(scoreLabel);
        song = new Sound("Cyborg.wav");
        bling = new Sound("coin.wav");

        // Editing items in games
        scoreLabel.setVisible(true);
        scoreLabel.setBounds(Driver.WIDTH/2 - 250, 10, 600, 50); // Played around with numbers for size
        scoreLabel.setForeground(Color.YELLOW);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 50));

        //End game quote
        quote = new JLabel("", SwingConstants.CENTER);
        quote.setBounds(Driver.WIDTH/2 - 300, Driver.HEIGHT / 2, 800, 50); // fix- not always centered
        quote.setForeground(Color.BLACK);
        quote.setFont(new Font("Arial", 10, 25));
        quote.setVisible(false);//not shown till end of game
        this.add(quote);

        // Other necessary private variables
        startTime = (int) System.currentTimeMillis();
        pipeList = new ArrayList<Pipe>();
        pipeList.add(new Pipe());
        bearList = new ArrayList<Bear>();
        fishList = new ArrayList<Fish>();
        score = 0;
        loadCoin = new Coin();
        loadFish = new Fish();//just to load the fish image only one time
    }

    // Pre: All the private variables have been properly instantiated
    // Post: Runs the game until the game ends
    public void playGame()
    {
        boolean over = false;
        boolean text = false;

        song.play();
        while(!over)
        {
            player.move(); // Moves player up or down

            for (Pipe p: pipeList)
            {
                p.move();
                if(p.getCoin() != null) // Controls coin catching
                    catchCoin(player, p.getCoin());
            }

            for (Bear b: bearList)
            {
                b.move(player.getX());
            }

            for (Fish f: fishList)
            {
                catchFish(player, f);
                f.move();
            }

            try
            {
                Thread.sleep( 10 );//pause for 10 milliseconds
                timer += 10;
                scoreLabel.setText("Score: " + score);
            }
            catch (InterruptedException ex){}

            if (!player.powered() && collisionChecker() == true)
            {
                over = true;
                scoreLabel.setText("GAME OVER. Score: " + score);
                song.stop();
            }

            if (timer % 2000 == 0)
                pipeList.add(new Pipe());
            if(timer % 8000 == 0)
                bearList.add(new Bear(Driver.WIDTH - Bear.WIDTH, Driver.HEIGHT - Bear.HEIGHT));          
            if(timer % 14500 == 0 && player.powered())//initally 14500
                player.powerDown();
            if(timer % 13000 == 0 && !player.powered())//initially 12000
                fishList.add(new Fish(Driver.WIDTH - Fish.DIAM, Driver.HEIGHT - Fish.DIAM));

            removePipe();
            removeBear();

            this.repaint();//redraw the screen with the updated locations; calls paintComponent below*/
        }
    }

    // Pre: executed when repaint() or paintImmediately is called
    // Post: the screen has been updated with current player location
    public void paintComponent( Graphics page )
    {
        back.paint(page);
        player.draw(page);
        for (Pipe p: pipeList)
            p.draw(page);

        for(Bear b: bearList)
            b.draw(page);

        for(Fish f: fishList)
            f.draw(page);
    }

    // Pre: event exists
    // Post: Controls movement if a key isn't pressed
    public void keyReleased( KeyEvent event )
    {         
        player.setUp(false);
        player.setDown(false);
    }

    // Pre: event exists
    // Post: Tells player whether to go up or fall
    public void keyPressed( KeyEvent event )
    {
        if( event.getKeyCode() == KeyEvent.VK_UP )
        {
            player.setUp(true);
        }

        if( event.getKeyCode() == KeyEvent.VK_SPACE )
        {
            if(song.isPlaying())
                song.stop();
            else
                song.loop();
        }
    }

    // Pre: None
    // Post: Nothing (must be present because KeyListener is implemented)
    public void keyTyped( KeyEvent event )
    {
    }

    // Pre: a and c exist
    // Post: Determines if the player has caught a coin and if so, removes the coin
    public void catchCoin(Player a, Coin c)
    {
        int coinX = c.getX();
        int coinY = c.getY();
        int playerX = a.getX();
        int playerY = (int)(a.getY());
        boolean checkX = playerX + Player.WIDTH >= coinX && playerX <= coinX;
        boolean checkY1 = (playerY <= (coinY + Coin.DIAM)) && (playerY + 100 >= coinY); // Coming up
        boolean checkY2 = (playerY + Player.WIDTH >= coinY) && (playerY <= coinY); // Coming down
        if (checkX && (checkY1 || checkY2) ||(a.powered() && playerX + 275 > coinX))//fix this last part, getting coins when powered up
        {
            if(c.counts()){
                if(song.isPlaying())//only makes coin noise if song is playing
                    bling.play();
                score++;
            }
            c.remove();
        }
    }

    // Pre: a and f exist
    // Post: Determines if the player has caught a fish and if so, applies the respective power ups to the player and removes the fish
    public void catchFish(Player a, Fish f)
    {
        int fishX = f.getX();
        int fishY = f.getY();
        int playerX = a.getX();
        int playerY = (int)(a.getY());
        boolean checkX = playerX + Player.WIDTH >= fishX && playerX <= fishX;
        boolean checkY1 = (playerY <= (fishY + Fish.DIAM)) && (playerY + Player.HEIGHT >= fishY); // Coming up
        boolean checkY2 = (playerY + Player.HEIGHT >= fishY) && (playerY <= fishY); // Coming down
        if (checkX && (checkY1 || checkY2))
        {
            if(f.counts())
            {
                bling.play();
                a.powerUp();
            }

            for(Fish fish: fishList)
            {
                fish.remove(); // Only one fish on the screen anyways
            }
        }
    }

    // Pre: b exists and xCoord and yCoord >= 0
    // Post: Determines if the player has collided with a bear
    public boolean bearCollide(Bear b, int xCoord, int yCoord)
    {
        int bearX = b.getX();
        int bearY = (int)b.getY();
        int penguinX = xCoord;
        int penguinY = yCoord;
        boolean checkX = (penguinX + Player.WIDTH >= bearX) && (penguinX <= bearX + Bear.WIDTH);
        boolean checkY1 = (penguinY <= (bearY + Bear.HEIGHT) && (penguinY + Player.HEIGHT >= bearY)); //coming up
        boolean checkY2 = (penguinY + Player.HEIGHT >= bearY) && (penguinY <= bearY + Bear.HEIGHT); //falling down
        if (checkX && (checkY1 || checkY2))
            return true;
        else 
            return false;
    }

    // Pre: pipeList has been instatiated
    // Post: Removes the first pipe in the list if it is off-screen
    public void removePipe()
    {
        if(pipeList.get(0).getX() + Pipe.PIPE_WIDTH <= 0){
            pipeList.remove(0);
        }
    }

    // Pre: bearList has been instatiated
    // Post: Removes the first bear in the list if it is off-screen
    public void removeBear()
    {
        if(bearList.size() > 0 && bearList.get(0).getX() + Bear.WIDTH <= 0)
            bearList.remove(0);
    }

    // Pre: None
    // Post: Checks if the player has collided with a pipe or bear, and calls showQuote(String) with cause of death
    public boolean collisionChecker()
    {
        int playerX = player.getX();
        int playerY = (int)(player.getY());
        int width = 45; // Used screen ruler to get penguin dimension
        int height = 90;
        for (Pipe p : pipeList)
        {
            if (p.inside(playerX + width, playerY) || p.inside(playerX + width, playerY + height) || p.inside(playerX + width/2, playerY + height) || p.inside(playerX + width/2, playerY) || p.inside(playerX, playerY) || p.inside(playerX, playerY + height))
            {
                showQuote("pipe");
                return true;
            }
        }

        for (Bear b : bearList)
        {
            if (bearCollide(b, playerX, playerY) == true)
            {
                showQuote("bear");
                return true;
            }
        }
        return false;
    }   

    //Pre: quote JLabel created based on cause of death and randomness, needs cause of death
    ///Post: shows random quote on screen depending on cause of death
    public void showQuote(String cod)
    {
        if(cod.equals("pipe")){
            int num = (int)(Math.random() * 5);//random number to decide quote
            if(num == 0)
                quote.setText("What's a penguin's least favorite vegetable? Iceberg lettuce!");
            if(num == 1)
                quote.setText("The Titanic should be renamed \"Flappy Penguin\".");
            if(num == 2)
                quote.setText("What are you thinking? Penguins can't fly!");
            if(num == 3)
                quote.setText("It's okay, just put some ice on it and it'll heal.");
            if(num == 4)
                quote.setText("nICE job.");
        }

        if(cod.equals("bear")){
            int num = (int)(Math.random() * 5);//random number to decide quote
            if(num == 0)
                quote.setText("I hope your loss isn't unBEARable.");
            if(num == 1)
                quote.setText("This game was programmed by my BEAR hands.");
            if(num == 2)
                quote.setText("What are you thinking? Penguins can't fly!");
            if(num == 3)
                quote.setText("Now we have to find a place to BEARy the penguin.");
            if(num == 4)
                quote.setText("Looks like you didn't do BEARy well.");
        }
        quote.setVisible(true);
    }
}