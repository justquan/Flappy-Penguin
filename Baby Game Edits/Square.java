import java.awt.*;//must be imported to use Graphics and Color

public class Square
{
    private int sx;                  // x position
    private int sy;                  // y position
    private int vx;                 // x velocity
    private int vy;                 // y velocity
    private int width = 125;
    private int height = 125;
    private int r = 225;
    private int g = 0;
    private int b = 0;
    
    public Square( int xLoc, int yLoc )
    {
        sx = xLoc;
        sy = yLoc;
        vx = 0;
        vy = 0;
    }
    
    public void move(int px, int py)
    {
        while (sx < 0){//fix square boundaries
            sx = 0;
        }
        while(sx > 1000- width){
            sx = 1000 - width;
        }
        while(sy < 0){
            sy = 0;
        }
        while(sy > 800 - height){
            sy = 800 - height;
        }

      if((px - sx) >= 2){
          sx = sx + 2;
      }
      else if((px - sx) == 1){
          sx = sx + 1;
      }
      
      else if((px - sx) <= -2){
          sx = sx - 2;
      }
      else if((px - sx) == -1){
          sx = sx - 1;
      }      
      
      if((py - sy) >= 2){
          sy = sy + 2;
      }
      else if((py - sy) == 1){
          sy = sy + 1;
      }

      else if((py - sy) <= -2){
          sy = sy - 2;
      }
      else if((py - sy) == -1){
          sy = sy - 1;
      }      
    }
    
    public void draw( Graphics page )
    {
        page.setColor( new Color( r, g, b ) );//color defined using rgb values (0-255 each)
        page.fillRect( sx, sy, width, height );//change the last two numbers and see what happens
    }
    
    public void run(int px, int py){
        r = (int)(Math.random() * 255) + 1;
        g = (int)(Math.random() * 255) + 1;
        b = (int)(Math.random() * 255) + 1;
        
      while (sx < 0){//fix square boundaries
            sx = sx + 7;
        }
      while(sx > 1000- width){
            sx = sx - 7;
        }
      while(sy < 0){
            sy = sy + 7;
        }
      while(sy > 800 - height){
            sy = sy - 7;
        }  
      
        if((px - sx) >= 3){
          sx = sx - 7;
      }
      
      else if((px - sx) <= -3){
          sx = sx + 7;
      }
      
      if((py - sy) >= 3){
          sy = sy - 7;
      }
      
      else if((py - sy) <= -3){
          sy = sy + 7;
      }
    }
    public int getx(){
        return sx;
    }
    
    public int gety(){
        return sy;
    }
}