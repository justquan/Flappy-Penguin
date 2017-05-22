import java.awt.*;//must be imported to use Graphics

public class Pipe
{
    private int xCoord;
    private int yCoord;
    private Coin myCoin;
    public static final int GAP = 210;
    public static final int PIPE_WIDTH = 125;
    public static final int CLEARANCE = 100;
    // The following private variables are used for polygon drawing
    private int [] xPoints;
    private int [] yPointsTop;
    private int [] yPointsBottom;
    private int nPoints;

    public Pipe()
    {
        xCoord = Driver.WIDTH - PIPE_WIDTH; // Top left corner of the pipe object
        yCoord = 0; // Top left corner of the pipe object

        int topHeight = (int) ((Math.random() * (Driver.HEIGHT - GAP - CLEARANCE))) + CLEARANCE;
        int bottomHeight = Driver.HEIGHT - GAP - topHeight - yCoord;

        xPoints = new int [] {xCoord, xCoord + PIPE_WIDTH, (2*xCoord + PIPE_WIDTH)/2};
        yPointsTop = new int [] {yCoord, yCoord, yCoord + topHeight};
        yPointsBottom = new int [] {Driver.HEIGHT, Driver.HEIGHT, Driver.HEIGHT - bottomHeight};
        nPoints = 3; // Need 3 points to draw a triangle

        int coinX = (2*xCoord + PIPE_WIDTH)/2 - Coin.DIAM/2;
        int coinY = topHeight + (GAP/2) - Coin.DIAM/2;
        myCoin = new Coin(coinX, coinY);
    } 

    public void move(){
        xCoord -= 2;
        for (int i = 0; i < xPoints.length; i++)
            xPoints[i] -= 2;
        if(myCoin != null)
            myCoin.move();
    }

    public void draw( Graphics page )
    {
        page.setColor(Color.WHITE);//color defined using rgb values (0-255 each)
        page.fillPolygon(xPoints, yPointsTop, nPoints);
        page.fillPolygon(xPoints, yPointsBottom, nPoints);
        myCoin.draw(page);//fix
    }

    public int getX(){
        return xCoord;
    }

    public int getY(){
        return yCoord;
    }

    public Coin getCoin()
    {
        return myCoin;
    }

    public void removeCoin()
    {
        myCoin = null;
    }
}