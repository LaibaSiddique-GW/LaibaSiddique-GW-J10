import java.util.Random;
import java.lang.Math;
public abstract class Creature {
    // Note, that output should be in (x,y) format as
    // the plotter expects it in that format.


  // dir: 0=North, 1=East, 2=South, 3=West.
    // 
    //
    //
    //               N (r-1,c+0)
    //               0
    //(r+0,c-1) W 3 [ ]  1 E (r+0,c+1)
    //               2
    //               S (r+1,c+0)
    //
    //
    // 
    // 
    
    public final static int NORTH = 0;
    public final static int EAST = 1;
    public final static int SOUTH = 2;
    public final static int WEST = 3;
    public final static int NUM_DIRS = 4;
    public final static int[] DIRS = {NORTH,EAST,SOUTH,WEST};

    //Use the index of the direction to determine how to add to a row or column
    //For example, if NORTH (index 0), the we subtract 1 from Y, and add 0 to X
    //direction
    protected final int[] dirY = {-1, 0, 1, 0};
    protected final int[] dirX = {0, 1, 0, -1};

    //Point Colors -- handy contests to use to make your code more readiable
    public final static char LAB_BLACK='k';
    public final static char LAB_BLUE='b';
    public final static char LAB_RED='r';
    public final static char LAB_YELLOW='y';
    public final static char LAB_ORANGE='o';
    public final static char LAB_PINK='p';
    public final static char LAB_MAGENTA='m';
    public final static char LAB_CYAN='c';
    public final static char LAB_GREEN='g';
    public final static char LAB_GRAY='e';


    //current direction facing
    private int dir;
    // setter
    public void setDir(int newDir) {
        this.dir = newDir;
    }
  
    public int getDir() {
        return this.dir;
    }

    //current point in grid
    private GridPoint point;

    //current color label for the point
    protected char lab;

    //City in which this creature lives so that it can update it's
    //location and get other information it might need (like the
    //location of other creatures) when making decisions.    
    protected City city;

    //boolean to set when this creature is dead
    protected boolean dead;

    //how wide the steps are
    protected int stepLen;

    public Creature(int x, int y, City cty){
        point = new GridPoint(x,y);
        city = cty;
        lab = LAB_GRAY;
        dir = city.getNextDir();
        dead= false;
        stepLen=1;
    }

    public boolean isDead(){ 
        return dead;
    }
    
    //getter methods
    public int getY(){
        return point.y;
    }
    public int getX(){
        return point.x;
    }
    public GridPoint getGridPoint(){
        return new GridPoint(point); //return a copy to preseve
                                     //encapsulation
    }

    public void randomTurn() {
        this.dir = city.getNextDir();
    }

    //BEGIN TODO: any additional methods you may need
    public void step() {
       point.x += dirX[dir] * stepLen;
       point.y += dirY[dir] * stepLen;
       
        if (point.x < 0){ 
            point.x = city.WIDTH - Math.abs(point.x);
        }
        if (point.y < 0){ 
            point.y = city.HEIGHT - Math.abs(point.y);
        }
        if (point.x >= city.WIDTH) {
            point.x = Math.abs(point.x) - city.WIDTH;
        }
        if (point.y >= city.HEIGHT){ 
            point.y = Math.abs(point.y) -  city.HEIGHT;
        }
    }
    //takeAction();
    public abstract void takeAction();
    //END TODO


    //output a creature to the plotter
    public String toString() {
        //output in col,row format or (x,y) format
        return "" + this.point.x + " " + this.point.y + " " + lab;
    }
}
