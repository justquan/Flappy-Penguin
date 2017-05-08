import java.awt.*;//must be imported to use Graphics

public class Block{
    
    private int dx;
    private int dy;
    
    public Block(){
        dx = (int)((Math.random() * 800) + 1);
        dy = (int)((Math.random() * 800) + 1);
    } 
    
    public void changePlace(){
        dx = (int)((Math.random() * 761) + 40);
        dy = (int)(Math.random() * 761);
    }
    public void draw( Graphics page )
    {
        page.setColor( new Color( 0, 255, 255 ) );//color defined using rgb values (0-255 each)
        page.fillRect( dx, dy, 40, 40 );//change the last two numbers and see what happens
    }
    
    public int getx(){
        return dx;
    }
    
    public int gety(){
        return dy;
    }
}