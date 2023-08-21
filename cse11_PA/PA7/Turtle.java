/**
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A17432488
 * Sources used: None
 * This file will create a turtle object.
 */
import java.awt.Color;

/**
 * This class will create a turtle object.
 */

public class Turtle extends Critter {
    //turtle vars
    /* Constants (Magic numbers) */
    private static final String SPECIES_NAME = "Tu";
    private final static String EMPTY = " ";
    private final static String FOOD = ".";
    private final static int TWO = 2;

    /**
     * Default constructor - creates turtle with the name Tu
     */
    public Turtle() {
        super(SPECIES_NAME);
    }

    /**
     * Returns the move of the turtle
     * 
     * @return Move west
     */
    @Override
    public Direction getMove() {
        return Direction.WEST;
    }

    /**
     * Returns the color of the turtle
     * 
     * @return Color green
     */
    @Override 
    public Color getColor() {
        return Color.GREEN;
    }

    /**
     * Returns the attack of the turle
     * 
     * @param opponent - the opponent turtle is fighting.
     * @return attack type
     */
    @Override 
    public Attack getAttack(String opponent){
        int randNum = (int)(Math.random()*TWO);
        if(randNum == 0){
            return Attack.ROAR;
        }
        else{
            return Attack.FORFEIT;
        }
    }

    /**
     * Returns whether or not the turtle is eating
     * 
     * @return eating
     */
    @Override
    public boolean eat() {
        //need to add if it is food or blank space
        // if(info.getNeighbor(getMove()).equals("Tu") ||
        //    info.getNeighbor(getMove()).equals(".")){
        if(!info.getNeighbor(Direction.NORTH).equals(EMPTY) &&
            !info.getNeighbor(Direction.NORTH).equals(FOOD) &&
            !info.getNeighbor(Direction.NORTH).equals(SPECIES_NAME)){
            
            return false;
        }
        if(!info.getNeighbor(Direction.SOUTH).equals(EMPTY) &&
            !info.getNeighbor(Direction.SOUTH).equals(FOOD) &&
            !info.getNeighbor(Direction.SOUTH).equals(SPECIES_NAME)){
            
            return false;
        }
        if(!info.getNeighbor(Direction.EAST).equals(EMPTY) &&
            !info.getNeighbor(Direction.EAST).equals(FOOD) &&
            !info.getNeighbor(Direction.EAST).equals(SPECIES_NAME)){
            
            return false;
        }
        if(!info.getNeighbor(Direction.WEST).equals(EMPTY) &&
            !info.getNeighbor(Direction.WEST).equals(FOOD) &&
            !info.getNeighbor(Direction.WEST).equals(SPECIES_NAME)){
            
            return false;
        }
        return true;
    }
}
