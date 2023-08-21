/**
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A17432488
 * Sources used: None
 * This file will create an elephant object.
 */
import java.awt.Color;

/**
 * This class will create an elephant object.
 */

public class Elephant extends Critter{
    //elephant vars
    /* Constants (Magic numbers) */
    private static final String SPECIES_NAME = "El";
    private final static int TWO = 2;
    protected static int goalX = 0;
    protected static int goalY = 0;
    /**
     * Default constructor - creates elephant with the name "El"
     */
    public Elephant() {
        super(SPECIES_NAME);
    }
    /**
     * Returns the color of the Elephant
     * 
     * @return Color gray
     */
    @Override 
    public Color getColor() {
        return Color.GRAY;
    }
    /**
     * Returns if the elephant will eat
     * 
     * @return true will eat
     */
    @Override
    public boolean eat(){
        return true;
    }

    /**
     * Changes the level when mate
     * 
     */
    @Override
    public void mate(){
        level += TWO;
    }
    /**
     * Resets the X and Y goals
     * 
     */
    @Override
    public void reset(){
        goalX = 0;
        goalY = 0;
    }

    /**
     * Returns the move that the elephant is doing
     * 
     * @return the direction the elephant moves
     */
    @Override 
    public Direction getMove() {
        if(goalX == info.getX() && goalY == info.getY()){
            //want to make it so new goals
            goalX = random.nextInt(info.getWidth());
            goalY = random.nextInt(info.getHeight());
        }
        if(((goalX - info.getX()) * (goalX - info.getX())) > 
            ((goalY - info.getY()) * (goalY - info.getY()))){
            // x is further
            if(goalX - info.getX() > 0)
                return Direction.EAST;
            else
                return Direction.WEST;
        }
        else if(((goalX - info.getX()) * (goalX - info.getX())) < 
            ((goalY - info.getY()) * (goalY - info.getY()))){
            // y is further
            if(goalY - info.getY() > 0)
                return Direction.SOUTH;
            else
                return Direction.NORTH;
        }
        else{
            //they are the same... so move x
            if(goalX - info.getX() > 0)
                return Direction.EAST;
            else
                return Direction.WEST;
        }
    }
}
