package edu.curtin.emergencysim.control.types;

/* *******************************************************************
* File:       FireEmg.java
* Author:     G.G.T.Shashen
* Created:    28/06/2022
* Modified:   2/06/2022
* Desc:       Fire type class implmentation
***********************************************************************/
public class FireEmg {
    // constant declaration and initlization
    private static final long FIRE_LOW_TO_HIGH = 3;
    private static final long FIRE_LOW_CLEANUP_TIME = 5;
    private static final long FIRE_HIGH_TO_LOW_TIME = 3;
    private static final double FIRE_LOW_CASUALTY_PROB = 0.1;
    private static final double FIRE_LOW_DAMAGE_PROB = 0.5;
    private static final double FIRE_HIGH_CASUALTY_PROB = 0.6;
    private static final double FIRE_HIGH_DAMAGE_PROB = 0.8;
    private int casualty;
    private int damage;
    private boolean high = false;

    // calculate a random probability between 0.0 - 1.0
    public double calProb() {
        return Math.round(Math.random() * 10.0) / 10.0;
    }

    // return the fire low to high constant
    public long getFireLowToHigh() {
        return FIRE_LOW_TO_HIGH;
    }

    // return the fire low cleanup time constant
    public long getFireLowCleanupTime() {
        return FIRE_LOW_CLEANUP_TIME;
    }

    // return the fire high to low constant
    public long getFireHighToLowTime() {
        return FIRE_HIGH_TO_LOW_TIME;
    }

    // return the fire low casualty probability constant
    public static double getFireLowCasualtyProb() {
        return FIRE_LOW_CASUALTY_PROB;
    }

    // return the fire low damage probability constant
    public static double getFireLowDamageProb() {
        return FIRE_LOW_DAMAGE_PROB;
    }

    // return the fire high casualty probability constant
    public static double getFireHighCasualtyProb() {
        return FIRE_HIGH_CASUALTY_PROB;
    }

    // return the fire high damage probability constant
    public static double getFireHighDamageProb() {
        return FIRE_HIGH_DAMAGE_PROB;
    }

    // return the calculated fire low casualty probabilty
    public int calFireLowCasualtyProb() {
        // check if the random probability is equal to constant
        if (calProb() == getFireLowCasualtyProb()) {
            // increase casualty if equal
            casualty++;
        }
        return casualty;
    }

    // return the calculated fire low damage probability
    public int calFireLowDamageProb() {
        // check if the random probability is equal to constant
        if (calProb() == getFireLowDamageProb()) {
            // increase damage if equal
            damage++;
        }
        return damage;
    }

    // return the calculated fire high casualty probability
    public int calFireHighCasualtyProb() {
        // check if the random probability is equal to constant
        if (calProb() == getFireHighCasualtyProb()) {
            // increase casualty if equal
            casualty++;
        }
        return casualty;
    }

    // return the calculated fire high damage probability
    public int calFireHighDamageProb() {
        // check if the random probability is equal to constant
        if (calProb() == getFireHighDamageProb()) {
            // increase damage if equal
            damage++;
        }
        return damage;
    }

    // return isHigh 
    public boolean isHigh() {
        return high;
    }

    // set high 
    public void setHigh(boolean high) {
        this.high = high;
    }

}
