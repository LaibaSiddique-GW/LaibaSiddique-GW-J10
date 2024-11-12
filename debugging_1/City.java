import java.util.*;

public class City{

    // used to implement randomization
    private int[] creatureXs = new int[10000];
    private int[] creatureYs = new int[10000];
    private int[] randomTurns = new int[10000];
    private int[] randomDirs = new int[10000];
    private int nextX;
    private int nextY;
    private int nextTurn;
    private int nextDir;

    //Determine the City Grid based on the size of the Plotter
    public static final int WIDTH = Plotter.WIDTH/PlotterPoint.POINT_WIDTH;
    public static final int HEIGHT = Plotter.WIDTH/PlotterPoint.POINT_WIDTH;

    
    // The Grid World for your reference
    //
    //        
    //       (x)
    //        0 1 2 3 4 5 ... WIDTH
    //       .----------------...
    //  (y) 0|           ,--y
    //      1|      * (3,1) 
    //      2|         ^    
    //      3|         '-x
    //      .|
    //      .|
    //      .|       
    //HEIGHT :
    //



    //-------------------------------------
    //The simulation's Data Structures
    //
    public List<Creature> creatures; //list of all creatures
    public Queue<Creature> creaturesToAdd;
    private Random rand;
    
    public City(Random rand, int numMice, int numCats) {
        this.rand = rand;

        generateRandomLists();

        //Intitialize the data structures and populate the city with initial 
        //creatures.
        this.creatures = new LinkedList<Creature>();
        this.creaturesToAdd = new LinkedList<Creature>();
        
        for (int i=0; i<numMice; i++) 
            addMouse();
        for (int i=0; i<numCats; i++) 
            addCat();
        addNewCreatures();
    }

    // this method populates lists of random numbers used by the creatures
    private void generateRandomLists(){
        for(int i = 0; i < 10000; i++){
            creatureXs[i] = Math.abs(this.rand.nextInt(WIDTH));
            creatureYs[i] = Math.abs(this.rand.nextInt(HEIGHT));
            randomTurns[i] = Math.abs(this.rand.nextInt(10));
            randomDirs[i] = Math.abs(this.rand.nextInt(4));
        }
    }

    public int getNextX(){
        int next = creatureXs[nextX];
        nextX++;
        return next;
    }

    public int getNextY(){
        int next = creatureYs[nextY];
        nextY++;
        return next;
    }

    public int getNextRandomTurn(){
        int next = randomTurns[nextTurn];
        nextTurn++;
        return next;
    }

    public int getNextDir(){
        int next = randomDirs[nextDir];
        nextDir++;
        return next;
    }

    //Return the current number of creatures in the simulation
    public int numCreatures(){
        return creatures.size();
    }
    
    public void addMouse(){
        int x = getNextX();
        int y = getNextY();

        Mouse m = new Mouse(x,y,this);
        creaturesToAdd.add(m);
    }

    public void addMouse(int x, int y){
        Mouse m = new Mouse(x,y,this);
        creaturesToAdd.add(m);
    }

    public void addCat(){
        int x = getNextX();
        int y = getNextY();
        creaturesToAdd.add(new Cat(x,y,this));
    }
    
    public void addNewCreatures(){
        while(!creaturesToAdd.isEmpty()){
            creatures.add(creaturesToAdd.remove());
        }
    }

    //You need to realize in your code such that simulate works for
    //**ALL** levels of simulation, which means you'll need to take
    //advantage of inheritance and polymorphism.
    public void simulate() {
        //DO NOT EDIT!
        
        //You get this one for free, but you need to review this to
        //understand how to implement your various creatures

        //First, for all creatures ...
        for(Creature c : creatures){
            c.step(); 
        } //move everyone forward one step in simulation

        //Second, for all cratures ...
        for(Creature c : creatures){
            c.takeAction(); 
        }//take some action based on the new positions

        //Third, for all creatures ...
        LinkedList<Creature> deadCreatures = new LinkedList<Creature>();
        for(Creature c: creatures){
            if(c.isDead()) deadCreatures.add(c);
        }//find those that are dead after the action is taken

        //Four, for all creatures ...
        for(Creature c: deadCreatures){
            creatures.remove(c);
        }//remove any creatures that are dead
        
        //Five, add in any new creatures that have been added before ...
        addNewCreatures();

        //Five, for all creatures
        for(Creature c : creatures){
            System.out.println(c);
        }//print out all creatures
    }
}
