package edu.curtin.emergencysim.control.types;

/* *******************************************************************
* File:       Flood.java
* Author:     G.G.T.Shashen
* Created:    28/06/2022
* Modified:   2/06/2022
* Desc:       Flood type class implmentation
***********************************************************************/
public class FloodEmg {
    // declare and initalize constants
    private static final long FLOOD_END_TIME = 15;
    private static final double FLOOD_CASUALTY_PROB = 0.5;
    private static final double FLOOD_DAMAGE_PROB = 0.8;
    private int floodDamage;
    private int floodCasualty;

    // calculate a random probability between 0.0 - 1.0
    public double calProb() {
        return Math.round(Math.random() * 10.0) / 10.0;
    }

    // return flood end time constant
    public long getFloodEndTime() {
        return FLOOD_END_TIME;
    }

    // return flood casualty probability constant
    public double getFloodCasualtyProb() {
        return FLOOD_CASUALTY_PROB;
    }

    // return the flood damage probability constant
    public double getFloodDamageProb() {
        return FLOOD_DAMAGE_PROB;
    }

    // return the calculated flood damage probability
    public int calFloodDamageProb() {
        // check if random probability is equal to constant
        if (calProb() == getFloodDamageProb()) {
            // increase damage if equal
            floodDamage++;
        }
        return floodDamage;
    }

    // return the calculated casualty probability
    public int calFloodCasualtyProb() {
        // check if random probability is equal to constant
        if (calProb() == getFloodCasualtyProb()) {
            // increase casualty if equal
            floodCasualty++;
        }
        return floodCasualty;
    }
}
