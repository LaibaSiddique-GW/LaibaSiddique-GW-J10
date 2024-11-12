import java.lang.Math;

public class Cat extends Creature {

    // dir: 0=North, 1=East, 2=South, 3=West.
    //
    //
    //
    // N (r-1,c+0)
    // 0
    // (r+0,c-1) W 3 [ ] 1 E (r+0,c+1)
    // 2
    // S (r+1,c+0)
    //
    //
    //
    // 

    private int movesSinceMouseEaten = 1;
    private Mouse targetMouse;

    public Cat(int x, int y, City city) {
        super(x, y, city);
        this.stepLen = 2;
        this.lab = LAB_YELLOW;
        //System.out.println("DEBUG 1: COLOR IS " + lab); // should be yellow right now. 
    }

    public void takeAction() {
        //System.out.println("DEBUG 2: ENTERED takeAction()");
        if (dead == true) return;
        //System.out.println("DEBUG 3: BEFORE CHECKING TO EAT");
        checkToEat();
        //System.out.println("DEBUG 4: After checking to eat and before finding closest mouse");
        findClosestMouse();

        if (targetMouse != null) {
            dirToTarget(targetMouse);
        }
    }

    public void step() {
        killCatMaybe();
        maybeTurn();
        movesSinceMouseEaten++;
        super.step();
    }

    private boolean findClosestMouse() {
        //System.out.println("Debug 4.1: ENTERED FINDCLOSESTMOUSE()");
        int targetDist = Integer.MAX_VALUE;
        boolean mouseFound = false;
        for (Creature c : city.creatures) {
            //System.out.println("DEBUG 4.2: ENTERED FOREACHCREATURE \n CURRENT TARGET MOUSE IS" + targetMouse); // targetMouse.toString() CAUSES NULLPOINTEREXCEPTION
            if (mouseInDistance(c) != -1 && mouseInDistance(c) < targetDist && (c instanceof Mouse)) {
                //System.out.println("DEBUG 4.3: ENTERED CRITERIA MET TO SETCREATURE \n CURRENT TARGET MOUSE IS" + targetMouse);
                setTargetMouse(c);
                //System.out.println("DEBUG 4.3: DONE SETTING CREATURE \n CURRENT TARGET MOUSE IS" + targetMouse);
                //System.out.println("THINK FOUND ISSUE: MOUSE STILL NULL NO MATTER WHAT: ISSUE IN SETTING THE TARGET MOUSE OR FINDING IT");
                targetDist = mouseInDistance(c);
                mouseFound = true;
            }
            // FIXING FAULT
            // } else // FIXING FAULT
            //     mouseFound = false; // FIXING FAULT
            //System.out.println("DEBUG END: FOUND THE FAULT HERE");
            // The problem was that whenever a mouse want not found, for each of the mice, it would reset the mouse to null and mouse found to false,
            // instead of keeping the already found set mouse as the mouse or replacing it with another mouse and keeping mousefound as true. 
            // Since if at least 1 mouse is found, then mousefound should stay at true rather than being reset to false whenver 1 of the many mice in not in distance. 

        }
        if (mouseFound) {
            return true;
        } else {
            setTargetMouse(null);
            return false;
        }
    }

    private int mouseInDistance(Creature c) {
        //System.out.println("DEBUG 4.2.1.1: ENTERED MOUSEINDISTANCE \n CURRENT TARGET MOUSE IS" + targetMouse);
        if (this.getGridPoint().dist(c.getGridPoint()) < 20 && (c instanceof Mouse)) {
            //System.out.println("DEBUG 4.2.1.2: ENTERED MOUSEFOUND IFSTATEMENT \n "); // Entered 
            return this.getGridPoint().dist(c.getGridPoint());
        } else {
            return -1;
        }

    }

    private void setTargetMouse(Creature c) {
        //System.out.println("DEBUG 4.3.1: ENTERED SETMOUSE \n CURRENT TARGET MOUSE IS" + targetMouse);
        if (c == null) {
            this.lab = LAB_YELLOW;
            this.targetMouse = null;
            return;
        }
        if (c instanceof Mouse) {
            this.targetMouse = (Mouse) c;
            this.lab = LAB_CYAN;
            return;
        }
        // This looks fine?
    }

    private void dirToTarget(Mouse target) {
        int vertDist = Math.abs(this.getY() - target.getY());
        int horizDist = Math.abs(this.getX() - target.getX());
        // if vertical distance strictly greater than horiz

        if (horizDist < vertDist) {
            if (target.getY() < this.getY()) {
                this.setDir(0);
                return;
            } else {
                this.setDir(2);
                return;
            }
        } else {
            if (this.getX() < target.getX()) {
                this.setDir(1);
                return;
            } else {
                this.setDir(3);
                return;
            }
        }
    }

    private void killCatMaybe() {
        // cat hasnt eaten mouse in 50 moves
        if (movesSinceMouseEaten == 50) {
            this.dead = true;
        }
    }

    private void checkToEat() {
        // Check if any mouse is at current x and y, to EAT
        if (targetMouse == null) {
            return;
        }
        if (this.getGridPoint().equals(targetMouse.getGridPoint())) {
            targetMouse.dead = true;
            movesSinceMouseEaten = 0;
            return;
        }
    }

    private void maybeTurn() {
        int randomNum = city.getNextRandomTurn();
        if (randomNum == 0) {
            this.randomTurn();
        }
    }
}
