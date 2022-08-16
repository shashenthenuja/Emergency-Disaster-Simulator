package edu.curtin.emergencysim.control.states;

import edu.curtin.emergencysim.control.Controller;
import edu.curtin.emergencysim.file.DisasterData;

/* *******************************************************************
* File:       Start.java
* Author:     G.G.T.Shashen
* Created:    28/06/2022
* Modified:   2/06/2022
* Desc:       Start state class implmentation
***********************************************************************/
public class Start implements EmgStates {

    private Controller control;

    public Start(Controller control) {
        this.control = control;
    }

    @Override
    public void lowIntensity(DisasterData data) {
        // do nothing
    }

    @Override
    public void highIntensity(DisasterData data) {
        // do nothing
    }

    @Override
    public void start(DisasterData data) {
        // start the emergency if it has not been started yet
        if (!data.hasStart()) {
            control.getResponder().send(data.getType() + " start " + data.getLocation());
            data.setStart(true);   
        }
        /* send casualty/damage/contamination information 
        if the current emergencies [flood/chemical] has been started accordingly */
        if (data.hasStart() && !data.hasEnd() && data.getType().equals("flood")) {
            // send casulty info only is the current emergency has not been atteneded
            if (!data.isAttended()) {
                control.getResponder().send(data.getType()+ " casualty " + data.getFlood().calFloodCasualtyProb() + " " + data.getLocation());
            }
            // send damage info
            control.getResponder().send(data.getType()+ " damage " + data.getFlood().calFloodDamageProb() + " " + data.getLocation());
        // send chemical info
        }else if (data.hasStart() && !data.hasEnd() && data.getType().equals("chemical")) {
            control.getResponder().send(data.getType()+ " casualty " + data.getChem().calChemCasualtyProb() + " " + data.getLocation());
            control.getResponder().send(data.getType()+ " contam " + data.getChem().calChemContamProb() + " " + data.getLocation());
        }
        /* set current state of flood emergency to end 
        if current time has been equal to flood end time constant */
        if (data.getType().equals("flood") && control.getTime() == data.getTime() + data.getFlood().getFloodEndTime()) {
            data.setEnd();
            control.setState(new End(control));
        }
        /* set current state of chemical emergency to end 
        if it has been attended and current time has been equal 
        to chemical clean up time consant */
        if (data.getType().equals("chemical")) {
            if (data.isAttended() && control.getTime() == data.getArrivedTime() + data.getChem().getChemCleanupTime()) {
                data.setEnd();
                control.setState(new End(control));
            }
            
        }
    }

    @Override
    public void end(DisasterData data) {
        // do nothing
    }
}
