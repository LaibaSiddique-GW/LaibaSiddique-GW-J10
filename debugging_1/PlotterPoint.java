import java.awt.*;

public class PlotterPoint{
    Point point;
    Dimension dimension;
    Color color;
    final int alpha=64;
    public final static int POINT_WIDTH = 10;
    public final static int POINT_HEIGHT = 10;
    public final static int OUTLINE_WIDTH = 2;

    public PlotterPoint(Point point, char color){
        this.point = point;
        this.dimension = new Dimension(POINT_WIDTH,POINT_HEIGHT);

        switch(color){
        case 'B':
        case 'b':
            this.color =  Color.BLUE;
            break;
        case 'R':
        case 'r':
            this.color = Color.RED;
            break;
        case 'Y':
        case 'y':
            this.color = Color.YELLOW;
            break;
        case 'O':
        case 'o':
            this.color = Color.ORANGE;
            break;
        case 'P':
        case 'p':
            this.color = Color.PINK;
            break;
        case 'M':
        case 'm':
            this.color = Color.MAGENTA;
            break;
        case 'G':
        case 'g':
            this.color = Color.GREEN;
            break;
        case 'C':
        case 'c':
            this.color = Color.CYAN;
            break;
        case 'E':
        case 'e':
            this.color = Color.GRAY;
            break;

        case 'K':
        case 'k':
        default:
            this.color = Color.BLACK;
            break;
        }
    }

    public void drawPoint(Graphics2D g) {
        this.drawPoint(g,false);
    }
    public void drawPoint(Graphics2D g, boolean trailing){

        if(!trailing) {
            if (!this.color.equals(Color.black))
                g.setColor(Color.black);
            else
                g.setColor(Color.white);
            g.setStroke(new BasicStroke(OUTLINE_WIDTH));
            g.drawRect(point.x, point.y, dimension.width, dimension.height);

            if(Plotter.DEBUG)
                g.drawString("("+point.x/POINT_WIDTH+","+point.y/POINT_HEIGHT+")",point.x,point.y);
        }

        g.setColor(trailing ? new Color(this.color.getRed(),this.color.getGreen(),this.color.getBlue(),alpha) : this.color);
        g.fillRect(point.x,point.y,dimension.width,dimension.height);
    }

    public String toString(){
        return ""+point.x+" "+point.y+" "+color;
    }
}
