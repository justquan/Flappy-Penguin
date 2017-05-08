import java.awt.*;//must be imported to use Graphics

public class Player
{
    private int px;
    private int py;
    private int diam = 100;
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;    
    private boolean down = false;    
    public Player( int pxLoc, int pyLoc )
    {
        px = pxLoc;
        py = pyLoc;
    }

    public void move(){
        if (left == true && px>0){
                px = px - 5;
            }
            
        if (right == true && px <= 1000 - diam){
                px = px + 5;
            }
            
        if (up == true && py >= 0){
                py = py - 5;
            }
        
        if (down == true && py <= 800 - diam){
                py = py + 5;
            }
        }
        
    public void setLeft(boolean press){//s
        left = press;
    }
    
        public void setRight(boolean press){//s
        right = press;
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
    
    public int gety(){
        return py;
    }
    
    public void draw( Graphics page )
    {
        page.setColor( Color.GREEN );
        page.fillRect( px, py, 100, 100 );//change the last two numbers and see what happens
    }
}