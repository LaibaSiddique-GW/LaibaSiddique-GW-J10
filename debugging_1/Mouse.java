public class Mouse extends Creature {
    /*
        After 20 rounds of simulation time, a mouse produces a new baby mouse at the same location
        A mouse dies after 22 rounds simulation time
        A mouse randomly changes directions 20% of the time. 
     */
    private int roundsAlive = 0;
    public Mouse(int x, int y, City city) {
        super(x, y, city);
        this.lab = LAB_BLUE;
        stepLen = 1;
    }
    
    public void takeAction(){
        if (roundsAlive == 20) {
            this.city.addMouse(this.getX(), this.getY());
        }
    }

    public void step() {
        roundsAlive++; 
        if (roundsAlive == 23) {
            this.dead = true;
            return;
        }
        // Change dir 20% of the time:     0 || 1 out of 0-9
        int randomNum = city.getNextRandomTurn();
        if (randomNum == 0 || randomNum == 1) {
            randomTurn();
        }
        super.step();
    }
}
