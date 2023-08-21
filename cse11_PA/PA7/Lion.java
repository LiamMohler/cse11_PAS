/**
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A17432488
 * Sources used: None
 * This file will create a lion object
 */

import java.awt.Color;


/**
 * This class will create a lion object
 */

public class Lion extends Feline{
    //lion vars
    /* Constants (Magic numbers) */
    private static final String SPECIES_NAME = "Lion";
    private final int NUMMOVES = 5;
    private final int TWO = 2;
    private final int ONE = 1;
    private final int THREE = 3;
    private static final String NOIL = "noiL";
    private int numFights;
    private int currDir;
    private int moveCount;
    /**
     * Default constructor - creates lion with the name "Lion"
     */
    public Lion() {
        super();
        displayName = SPECIES_NAME;
        numFights = 0;
        currDir = 0;
        moveCount = 0;
    }

    /**
     * Returns the color of the Lion
     * 
     * @return Color yellow
     */
    @Override 
    public Color getColor() {
        return Color.YELLOW;
    }

    /**
     * Returns the direction of move
     * 
     * @return Move direction
     */
    @Override
    public Direction getMove() {

        if(moveCount % NUMMOVES == 0 && moveCount != 0){
            if(currDir == THREE)
                currDir = 0;
            else
                currDir++;
        }
        moveCount++;
        if(currDir == 0)
            return Direction.EAST;
        else if(currDir == ONE)
            return Direction.SOUTH;
        else if(currDir == TWO)
            return Direction.WEST;
        else
            return Direction.NORTH;
            
    }    

    /**
     * Returns if the lion will eat or not.
     * 
     * @return whether to eat or not
     */
    @Override
    public boolean eat() {
        // TODO
        if(numFights > 0){
            numFights = 0;
            return true;
        }
        return false;
    }

    /**
     * Changes things about lion when sleep
     * 
     */
    @Override
    public void sleep() {
        // TODO
        numFights = 0;
        displayName = NOIL;
    }

    /**
     * Changes things about lion when wakes up
     * 
     */
    @Override
    public void wakeup() {
        // TODO
        displayName = SPECIES_NAME;
    }
    /**
     * Changes things about lion when win a fight
     * 
     */
    @Override
    public void win() {
        // TODO
        numFights++;
    }
    
}
