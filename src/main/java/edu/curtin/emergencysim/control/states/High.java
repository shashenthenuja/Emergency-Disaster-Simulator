package edu.curtin.emergencysim.control.states;

import edu.curtin.emergencysim.control.Controller;
import edu.curtin.emergencysim.file.DisasterData;

/* *******************************************************************
* File:       High.java
* Author:     G.G.T.Shashen
* Created:    28/06/2022
* Modified:   2/06/2022
* Desc:       High state class implementation
***********************************************************************/
public class High implements EmgStates{

    private Controller control;

    public High(Controller control) {
        this.control = control;
    }

    @Override
    public void lowIntensity(DisasterData data) {
        // do nothing
    }

    @Override
    public void highIntensity(DisasterData data) {
        // check if current fire emergency has started and time equals to the low to high constant
        if (data.hasStart() && control.getTime() == data.getTime() + data.getFire().getFireLowToHigh()) {
            control.getResponder().send(data.getType() + " high " + data.getLocation());
            data.getFire().setHigh(true);
        }
        // check if current fire emergency has started and currently is high
        if (data.hasStart() && !data.hasEnd() && data.getFire().isHigh()) {
            control.getResponder().send(data.getType()+ " casualty " + data.getFire().calFireHighCasualtyProb() + " " + data.getLocation());
            control.getResponder().send(data.getType()+ " damage " + data.getFire().calFireHighDamageProb() + " " + data.getLocation());
        }
        // check if the current fire emergency is attenede by rescuers has equals to the high to low constant
        if (data.isAttended() && control.getTime() == data.getArrivedTime() + data.getFire().getFireHighToLowTime()) {
            // set the current state to low
            control.setState(new Low(control));
        }
    }

    @Override
    public void start(DisasterData data) {
        // do nothing
        
    }

    @Override
    public void end(DisasterData data) {
        // do nothing
        
    }
    
}
