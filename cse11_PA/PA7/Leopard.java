/**
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A17432488
 * Sources used: None
 * This file will create a Leopard object
 */

import java.awt.Color;

/**
 * This class will create a leopard object
 */

public class Leopard extends Feline{
    //leopard vars
    /* Constants (Magic numbers) */
    private static final String SPECIES_NAME = "Lpd";
    private final static int TWO = 2;
    private final static int ONE = 1;
    private final static int THREE = 3;
    private final static int FOUR = 4;
    private final static int FIVE = 5;
    private final static int TEN = 10;
    private final static String TU = "Tu";
    private final static String PATRICK = "Patrick";
    private final static String PERIOD = ".";
    static protected int confidence;

    /**
     * Default constructor - creates leopard wih the name "Lpd"
     */
    public Leopard(){
        super();
        displayName = SPECIES_NAME;
    }

    /**
     * Returns the color of the Leopard
     * 
     * @return Color red
     */
    @Override 
    public Color getColor() {
        return Color.RED;
    }

    /**
     * Returns the move that Leopard will make
     * 
     * @return The direction fo the move
     */
    @Override
    public Direction getMove(){

        if(info.getNeighbor(Direction.NORTH).equals(PATRICK) || 
            info.getNeighbor(Direction.NORTH).equals(PERIOD)){
            return Direction.NORTH;
        }
        else if(info.getNeighbor(Direction.SOUTH).equals(PATRICK) || 
            info.getNeighbor(Direction.SOUTH).equals(PERIOD)){
            return Direction.SOUTH;
        }
        else if(info.getNeighbor(Direction.EAST).equals(PATRICK) || 
            info.getNeighbor(Direction.EAST).equals(PERIOD)){
            return Direction.EAST;
        }
        else if(info.getNeighbor(Direction.WEST).equals(PATRICK) || 
            info.getNeighbor(Direction.WEST).equals(PERIOD)){
            return Direction.WEST;
        }

        int randNum = (int)(Math.random()*FOUR);

        if(randNum == 0)
            return Direction.NORTH;
        else if(randNum == ONE)
            return Direction.SOUTH;
        else if(randNum == TWO)
            return Direction.EAST;
        else
            return Direction.WEST;
    } 
    
    /**
     * Returns if the leopard will eat
     * 
     * @return whether or not to eat
     */
    @Override
    public boolean eat() {
        // TODO
        if(confidence >= (int)(Math.random()*TEN)+ONE){
            return true;
        }
        return false;
    }

    /**
     * Adds to confidence if won fight
     * 
     */
    @Override
    public void win() {
        // TODO
        if(confidence < TEN)
            confidence++;
    }

    /**
     * Subtracts from confidence if lost fight
     * 
     */
    @Override
    public void lose() {
        // TODO
        if(confidence > 0)
            confidence--;
    }

    /**
     * Returns the attack leopard makes
     * 
     * @param opponent - critter fighting
     * @return the attack leopard makes
     */
    @Override
    public Attack getAttack(String opponent){
        if(opponent.equals(TU) || confidence > FIVE)
            return Attack.POUNCE;
        else
            return generateAttack(opponent);
    }


    /**
     * Resets confidence
     * 
     */
    @Override
    public void reset(){
        confidence = 0;
    }

    /**
     * Returns the attack leopard will make
     * 
     * @param opponent - the opponent
     * @return returns the attack
     */
    protected Attack generateAttack(String opponent){
        if(opponent.equals(PATRICK)){
            return Attack.FORFEIT;
        }

        int randNum = (int)(Math.random()*THREE);

        if(randNum == 0)
            return Attack.POUNCE;
        else if(randNum == ONE)
            return Attack.ROAR;
        else
            return Attack.SCRATCH;
    }

}
