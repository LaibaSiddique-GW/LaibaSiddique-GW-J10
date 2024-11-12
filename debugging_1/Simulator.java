import java.util.Random;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Simulator {
	
    public static void main(String[] args) {
        boolean DEBUG = false;
        
        //parse arguments
        if(args.length < 4){
            System.out.println("ERROR: missing arguments");
            System.out.println("java Simulator numMice numCats rounds [randSeed]");
            System.exit(1);
        }
        int numMice = Integer.parseInt(args[0]);
        int numCats = Integer.parseInt(args[1]);
        int rounds = Integer.parseInt(args[2]);

        Random rand;
        if(args.length > 3)
            rand = new Random(Integer.parseInt(args[3]));
        else
            rand = new Random(100);

        if(args.length > 4 && args[4].equals("--DEBUG")){
            DEBUG = true;
        }

        // Populate city 
        City city = new City(rand, numMice, numCats);
        int count = 0;

        while (count < rounds) {
            count++;
// Every 100 rounds, a mouse is added to a random location in the city
// Every 25 rounds, a cat is added to a random location in the city. 
// To pass the unit tests, you must rely on the city’s getNextX() and 
// getNextY() methods each time you need to generate these random X
// and Y coordinates.
//
//
            //TODO...
            // Every N rounds, add a mouse
            if (count % 100 == 0) {
                city.addMouse();
            }
            //Every M rounds, add a Cat
            //System.out.println("Debug 1: Round number:" + count);
            if (count % 25 == 0) {
                //System.out.println("Debug 1.1: Cat may be added at round number:" + count);
                city.addCat();
            }
            //END TODO
            //System.out.println("Debug 2: About to start simulating" + count);
            city.simulate();
            System.out.println("done " + count);
            System.out.flush();

            if(DEBUG){
                System.err.print("Enter anything to continue: ");
                try{
                    (new BufferedReader(new InputStreamReader(System.in))).readLine();
                }catch(Exception e){
                    System.exit(1);
                }
            }
        }
    }
}
