/**
 * Name: Liam Mohler
 * Email: lmohler@ucsd.edu
 * PID: A17432488
 * Sources used: None
 * This file will create a ocelot object
 */

import java.awt.Color;

/**
 * This class will create a ocelot object.
 */

public class Ocelot extends Leopard{
    //ocelot vars
    /* Constants (Magic numbers) */
    private static final String SPECIES_NAME = "Oce";
    private final static String FE = "Fe";
    private final static String LION = "Lion";
    private final static String NOIL = "noiL";
    private final static String LPD = "Lpd";
    /**
     * Default constructor - creates Ocelot with the name "Oce"
     */
    public Ocelot(){
        super();
        displayName = SPECIES_NAME;
    }

    /**
     * Returns the color of the Ocelot
     * 
     * @return Color light gray
     */
    @Override 
    public Color getColor() {
        return Color.LIGHT_GRAY;
    }
    

    /**
     * Returns attack of the ocelot
     * 
     * @param opponent - the oppenent of the ocelot
     * @return the attack of the ocelot
     */
    @Override 
    protected Attack generateAttack(String opponent) {
        if(opponent.equals(FE) || opponent.equals(LION) 
            || opponent.equals(LPD) || opponent.equals(NOIL)){
            return Attack.SCRATCH;
        }
        else{
            return Attack.POUNCE;
        }
    }
}
