import java.awt.*;//must be imported to use Graphics

public class Player
{
    private int px;
    private double py;
    private int diam = 75;
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;    
    private boolean down = false;
    public static final double g = .2;
    private double velocity;
    
    public Player( int pxLoc, int pyLoc )
    {
        px = pxLoc;
        py = pyLoc;
        velocity = 0.0;
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

    public int getx(){
        return px;
    }

    public double gety(){
        return py;
    }

    public void draw( Graphics page )
    {
        page.setColor( Color.BLACK );
        page.fillOval( px, (int)py, 75, 75 );//change the last two numbers and see what happens
    }
}