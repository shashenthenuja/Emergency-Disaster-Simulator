package edu.curtin.emergencysim.control.states;

import edu.curtin.emergencysim.control.Controller;
import edu.curtin.emergencysim.file.DisasterData;

/* *******************************************************************
* File:       End.java
* Author:     G.G.T.Shashen
* Created:    28/06/2022
* Modified:   2/06/2022
* Desc:       End state class implmentation
***********************************************************************/
public class End implements EmgStates {

    private Controller control;

    public End(Controller control) {
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
        // do nothing

    }

    @Override
    public void end(DisasterData data) {
        // check if the current data has ended
        if (data.hasEnd()) {
            // send data to the responder
            control.getResponder().send(data.getType() + " end " + data.getLocation()); 
        }
    }

}
