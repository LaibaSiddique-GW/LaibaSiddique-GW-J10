public class GridPoint extends Object{

    //-----------------
    //You get this class for free. You can edit it as you see fit for
    //your model, but do not change equals or hashcode
    //------------------
    
    //publically accessible x and y
    public int x, y;
    
    public GridPoint(int x, int y){
        this.x = x;
        this.y = y;
    }

    //copy construtor
    public GridPoint(GridPoint other){
        this.x = other.x;
        this.y = other.y;
    }

    //The following two methods let this be used as a Key in a HashMap
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof GridPoint)) return false;
        GridPoint other = (GridPoint) o;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode(){
        return this.x * 31 + this.y;
    }

    //Free two-string method prints out in (x,y) - (y,x) format
    @Override
    public String toString(){
        return "(" + this.y + "," + this.x + ")";
    }

    private int minDist(int a, int b, int n){
        return Math.min(Math.abs(a - b), //within
            (Math.min(a, b) - Math.max(a, b) + n)); //wraparound
    }

    //Distance to another gridPoint is the manhattan distance
    //number of xs away + number of ys away
    //accounts for wrapping around 
    public int dist(GridPoint other){
        return minDist(x, other.x, City.WIDTH) +  minDist(y, other.y, City.HEIGHT);
    }
}
