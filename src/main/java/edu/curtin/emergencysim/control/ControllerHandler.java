package edu.curtin.emergencysim.control;

import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import edu.curtin.emergencysim.control.states.High;
import edu.curtin.emergencysim.control.states.Low;
import edu.curtin.emergencysim.control.states.Start;
import edu.curtin.emergencysim.file.DisasterData;
import edu.curtin.emergencysim.file.FileRead;

/* *******************************************************************
* File:       ControllerHandler.java
* Author:     G.G.T.Shashen
* Created:    30/06/2022
* Modified:   2/06/2022
* Desc:       Controller Handler class implmentation
***********************************************************************/
public class ControllerHandler {
    private FileRead file;
    private Controller control;
    private static Logger logger = Logger.getLogger(ControllerHandler.class.getName());

    public ControllerHandler(FileRead file, Controller control) {
        this.file = file;
        this.control = control;
    }

    // method to execute the simulation
    public void simulate() throws ControllerException {
        List<DisasterData> list = file.getList();
        for (DisasterData data : list) {
            // check if each emergency time is equal to current time
            if (control.getTime() >= data.getTime()) {
                if (data.getType().equals("fire")) {
                    // set the current state to low when a fire starts
                    control.setState(new Low(control));
                    control.low(data);
                    control.high(data);
                    control.end(data);
                    // set the current state to high if the fire is high
                    if (data.getFire().isHigh()) {
                        control.setState(new High(control));
                    }
                } else if (data.getType().equals("flood")) {
                    // set the current state to start if emergency is a flood
                    control.setState(new Start(control));
                    control.start(data);
                    control.end(data);
                } else if (data.getType().equals("chemical")) {
                    // set the current state to start if emergency is chemical 
                    control.setState(new Start(control));
                    control.start(data);
                    control.end(data);
                } else {
                    throw new ControllerException("Invalid Type!");
                }
            }
        }
    }

    // method to check if the rescuers have arrived/departed
    public boolean attended() {
        List<DisasterData> list = file.getList();
        boolean end = true;
        for (String res : control.getResponder().poll()) {
            // check if regex patterns matches current data
            boolean m = Pattern.matches("end|(fire|flood|chemical) [+-] .+", res);
            if (m) {
                for (DisasterData data : list) {
                    // check if the rescuers have arrived/departed or end for each emergency
                    if (res.equals(data.getType() + " + " + data.getLocation())) {
                        data.setAttended(true);
                        data.setArrivedTime(control.getTime());
                    } else if (res.equals(data.getType() + " - " + data.getLocation())) {
                        data.setAttended(false);
                    } else if (res.equals("end")) {
                        end = false;
                    }
                }
            } else {
                logger.warning("Invalid Message");
            }
        }
        return end;
    }

}