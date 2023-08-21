/**
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A17432488
 * Sources used: None
 * This file will create a starfish object.
 */
import java.awt.Color;

/**
 * This class will create a Starfish object.
 */

public class Starfish extends Critter {
    //starfish vars
    /* Constants (Magic numbers) */
    private static final String SPECIES_NAME = "Patrick";

    /**
     * Default constructor - creates critter with name Patrick
     */
    public Starfish() {
        super(SPECIES_NAME);
    }

    /**
     * Returns the move of the starfish
     * 
     * @return Move Center
     */
    @Override
    public Direction getMove() {
        return Direction.CENTER;
    }

    /**
     * Returns the color of the Starfish
     * 
     * @return Color pink
     */
    @Override 
    public Color getColor() {
        return Color.PINK;
    }
}