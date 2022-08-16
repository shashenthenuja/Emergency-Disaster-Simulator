package edu.curtin.emergencysim.control.states;


import edu.curtin.emergencysim.control.Controller;
import edu.curtin.emergencysim.file.DisasterData;

/* *******************************************************************
* File:       Low.java
* Author:     G.G.T.Shashen
* Created:    28/06/2022
* Modified:   2/06/2022
* Desc:       Low state class implmentation
***********************************************************************/
public class Low implements EmgStates{

    private Controller control;

    public Low(Controller control) {
        this.control = control;
    }

    @Override
    public void lowIntensity(DisasterData data) {
        // check if the current fire emergency has not started 
        if (!data.hasStart() && !data.hasEnd()) {
            // start the initial fire state as low
            control.getResponder().send(data.getType() + " low " + data.getLocation());
            data.setStart(true);
        }
        // check if the current fire emergency is attended and start in low state if the previous state was high
        if(data.isAttended() && control.getTime() == data.getArrivedTime() + data.getFire().getFireHighToLowTime()) {
            control.getResponder().send(data.getType() + " low " + data.getLocation());
        }
        // check if the current fire emergency has started and send casualty and damage info
        if (data.hasStart() && !data.hasEnd()) {
            control.getResponder().send(data.getType()+ " casualty " + data.getFire().calFireLowCasualtyProb() + " " + data.getLocation());
            control.getResponder().send(data.getType()+ " damage " + data.getFire().calFireLowDamageProb() + " " + data.getLocation());   
        }
        // set the current state to high if the emergency is not attened and equals to the low to high constant
        if (control.getTime() == data.getTime() + data.getFire().getFireLowToHigh()) {
            control.setState(new High(control));
        }
        // set the current state to end if the current fire emergency has been atteneded and has not been high before
        if (data.isAttended() && !data.getFire().isHigh() &&  control.getTime() == data.getArrivedTime() + data.getFire().getFireLowCleanupTime()) {
            data.setEnd();
            control.setState(new End(control));
        }
        // set the current state to end if the current fire emergency has been atteneded and has been high before
        if (data.isAttended() && data.getFire().isHigh() && control.getTime() == (data.getArrivedTime()+data.getFire().getFireHighToLowTime()) + data.getFire().getFireLowCleanupTime()) {
            data.setEnd();
            control.setState(new End(control));
        }
        
    }

    @Override
    public void highIntensity(DisasterData data) {
        // do nothing
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
