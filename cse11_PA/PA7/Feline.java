/**
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A17432488
 * Sources used: None
 * This file will create a feline object
 */

/**
 * This class will create a feline object
 */
public class Feline extends Critter {
    //feline vars
    /* Constants (Magic numbers) */
    private static final String SPECIES_NAME = "Fe";
    private final int TWO = 2;
    private final int ONE = 1;
    private final int THREE = 3;
    private final int moveCountNum = 5;
    private final int randNumAmmount = 4;
    private int moveCount; //counter for getMove method before random direction
    private int eatCount; //counter for eating
    private Direction currDir; //current direction

    /**
     * Default constructor - creates Feline with the name "Fe"
     */
    public Feline() {
        // TODO
        super(SPECIES_NAME);
        moveCount = 0;
        eatCount = ONE;
    }

    /**
     * Returns the move of the Feline
     * 
     * @return move direction
     */
    @Override
    public Direction getMove() {
        // TODO
        if(moveCount % moveCountNum == 0 ){
            int randNum = (int)(Math.random()*randNumAmmount);
            if(randNum == 0)
                currDir = Direction.NORTH;
            else if(randNum == 1)
                currDir = Direction.EAST;
            else if(randNum == TWO)
                currDir = Direction.SOUTH;
            else
                currDir = Direction.WEST;
        }
        moveCount++;
        return(currDir);
    }    

    /**
     * Returns whether or not the feline is eating
     * 
     * @return eating
     */
    @Override
    public boolean eat() {
        // TODO
        if(eatCount % THREE == 0){
            eatCount++;
            return true;
        }
        eatCount++;
        return false;

    }

    /**
     * Returns the attack type of the feline
     * 
     * @param opponent - the string name of the opponent
     * @return the attack type
     */
    @Override
    public Attack getAttack(String opponent){
       // TODO
       return Attack.POUNCE;
    }
}