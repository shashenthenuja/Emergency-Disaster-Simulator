package edu.curtin.emergencysim.control;

import edu.curtin.emergencysim.control.states.EmgStates;
import edu.curtin.emergencysim.file.DisasterData;
import edu.curtin.emergencysim.responders.ResponderCommImpl;

/* *******************************************************************
* File:       Controller.java
* Author:     G.G.T.Shashen
* Created:    26/06/2022
* Modified:   2/06/2022
* Desc:       Controller class implmentation
***********************************************************************/
public class Controller {
    private ResponderCommImpl responder;
    private long time = 0;
    private EmgStates state;

    public Controller(ResponderCommImpl responder) {
        this.responder = responder;
    }

    // set the current time in the simulation
    public void setTime(long time) {
        this.time = time;
    }

    // return the current time in the simulation
    public long getTime() {
        return time;
    }

    // set a state
    public void setState(EmgStates state) {
        this.state = state;
    }

    // get the current state
    public EmgStates getState() {
        return state;
    }

    // method to execute the start method  in the current state
    public void start(DisasterData data) {
        state.start(data);
    }

    // method to execute the low intensity method in the current state
    public void low(DisasterData data) {
        state.lowIntensity(data);
    }

    // method to execute the high intensity method in the current state
    public void high(DisasterData data) {
        state.highIntensity(data);
    }

    // method to execute the end method in the current state
    public void end(DisasterData data) {
        state.end(data);
    }

    // return the responder
    public ResponderCommImpl getResponder() {
        return responder;
    }

}