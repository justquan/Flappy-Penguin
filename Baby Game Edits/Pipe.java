import java.awt.*;//must be imported to use Graphics

public class Pipe{

    private int dx;
    private int dy;
    public static final int GAP = 250;
    public static final int PIPE_WIDTH = 125;
    public static final int CLEARANCE = 100;
    private int topHeight;
    private int bottomHeight;

    public Pipe(){
        dx = Driver.WIDTH - PIPE_WIDTH;
        topHeight = (int) ((Math.random() * Driver.HEIGHT - GAP - CLEARANCE));
        bottomHeight = Driver.HEIGHT - GAP - topHeight - dy;
    } 

    public void draw( Graphics page )
    {
        page.setColor(Color.GREEN);//color defined using rgb values (0-255 each)
        page.fillRect( dx, dy, PIPE_WIDTH, topHeight );//top pipe
        page.fillRect( dx, dy + GAP + topHeight, PIPE_WIDTH, bottomHeight); // bottom pipe drawing 
    }

    public int getx(){
        return dx;
    }

    public int gety(){
        return dy;
    }
}