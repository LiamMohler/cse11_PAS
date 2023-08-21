/**
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A17432488
 * Sources used: None
 * This file will create a Liam_lmohler object
 */
import java.awt.Color;

/**
 * This class will create a Liam_lmohler object.
 */

public class Liam_lmohler extends Critter{
    //liam
    /* Constants (Magic numbers) */
    private static final String SPECIES_NAME = "Ray73H";
    private static final String PATRICK = "Patrick";
    private static final String TU = "Tu";
    private static final String PERIOD = ".";
    private static final String R = "R";
    private static final String SMALLR = "r";
    private static final String TIRED = "tired";
    private static final int TWO = 2;
    protected int numMoves;
    protected int dir;
    /**
     * Default constructor - creates Liam_lmohler with the name "Ray73H"
     */
    public Liam_lmohler(){
        super(SPECIES_NAME);
        dir = (int)(Math.random()*TWO);
        numMoves = 0;
    }
    /**
     * Returns the move of Liam
     * 
     * @return direction liam moves
     */
    @Override
    public Direction getMove() {
        numMoves++;


        if(info.getNeighbor(Direction.NORTH).equals(PATRICK) || 
            info.getNeighbor(Direction.NORTH).equals(TU) ||
            info.getNeighbor(Direction.NORTH).equals(PERIOD)|| 
            info.getNeighbor(Direction.NORTH).equals(R) ||
            info.getNeighbor(Direction.NORTH).equals(SMALLR)||
            info.getNeighbor(Direction.NORTH).equals(TIRED)){
            return Direction.NORTH;
        }
        else if(info.getNeighbor(Direction.SOUTH).equals(PATRICK)||
            info.getNeighbor(Direction.SOUTH).equals(TU) || 
            info.getNeighbor(Direction.SOUTH).equals(PERIOD)|| 
            info.getNeighbor(Direction.SOUTH).equals(R) ||
            info.getNeighbor(Direction.SOUTH).equals(SMALLR)||
            info.getNeighbor(Direction.SOUTH).equals(TIRED)){
            return Direction.SOUTH;
        }
        else if(info.getNeighbor(Direction.EAST).equals(PATRICK) ||
            info.getNeighbor(Direction.EAST).equals(TU) || 
            info.getNeighbor(Direction.EAST).equals(PERIOD)|| 
            info.getNeighbor(Direction.EAST).equals(R) ||
            info.getNeighbor(Direction.EAST).equals(SMALLR)||
            info.getNeighbor(Direction.EAST).equals(TIRED)){
            return Direction.EAST;
        }
        else if(info.getNeighbor(Direction.WEST).equals(PATRICK) ||
            info.getNeighbor(Direction.WEST).equals(TU) || 
            info.getNeighbor(Direction.WEST).equals(PERIOD) || 
            info.getNeighbor(Direction.WEST).equals(R) ||
            info.getNeighbor(Direction.WEST).equals(SMALLR) ||
            info.getNeighbor(Direction.WEST).equals(TIRED)){
            return Direction.WEST;
        }
            return Direction.EAST;
    }


    /**
     * Returns the attack of liam
     * 
     * @param opponent - the opponent
     * @return attack of liam
     */
    @Override
    public Attack getAttack(String opponent) {
        if(opponent.equals(TU)){
            return Attack.POUNCE;
        }
        return Attack.SCRATCH;
    }

    /**
     * Returns the color of the liam
     * 
     * @return Color magenta
     */
    @Override 
    public Color getColor() {
        return Color.magenta;
    }

    /**
     * Returns if liam will eat
     * 
     * @return true always 
     */
    @Override
    public boolean eat() {
        return true;
    }


}
