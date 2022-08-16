package edu.curtin.emergencysim.control.types;

/* *******************************************************************
* File:       ChemicalEmg.java
* Author:     G.G.T.Shashen
* Created:    28/06/2022
* Modified:   2/06/2022
* Desc:       Chemical Type class implmentation
***********************************************************************/
public class ChemicalEmg {
    // constant declaration and initlialization
    private static final long CHEM_CLEANUP_TIME = 20;
    private static final double CHEM_CASUALTY_PROB = 0.4;
    private static final double CHEM_CONTAM_PROB = 0.9;
    private int chemCasualty;
    private int chemEnvContam;

    // calculate a random probability between 0.0 - 1.0
    public double calProb() {
        return Math.round(Math.random() * 10.0) / 10.0;
    }

    // return the chemical clean up time constant
    public long getChemCleanupTime() {
        return CHEM_CLEANUP_TIME;
    }

    // return the chemical casualty probability constant
    public static double getChemCasualtyProb() {
        return CHEM_CASUALTY_PROB;
    }

    // return the chemical contamination probability constant
    public static double getChemContamProb() {
        return CHEM_CONTAM_PROB;
    }

    // calculate and return the random chemical casualty probability
    public int calChemCasualtyProb() {
        // check if the random probability is equal to constant
        if (calProb() == getChemCasualtyProb()) {
            // increase casualty if equal
            chemCasualty++;
        }
        return chemCasualty;
    }

    // calculate and the return the random chemical contamination probability
    public int calChemContamProb() {
        // check if the random probability is equal to constant
        if (calProb() == getChemContamProb()) {
            // increase contamincation if equal
            chemEnvContam++;
        }
        return chemEnvContam;
    }

}
