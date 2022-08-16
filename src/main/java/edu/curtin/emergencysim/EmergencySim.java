package edu.curtin.emergencysim;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import edu.curtin.emergencysim.control.Controller;
import edu.curtin.emergencysim.control.ControllerException;
import edu.curtin.emergencysim.control.ControllerHandler;
import edu.curtin.emergencysim.file.*;
import edu.curtin.emergencysim.responders.ResponderCommImpl;

/* *******************************************************************
* File:       EmergencySim.java
* Author:     G.G.T.Shashen
* Created:    24/06/2022
* Modified:   2/06/2022
* Desc:       Disaster simlation program
***********************************************************************/
public class EmergencySim
{
    private static Logger logger = Logger.getLogger(EmergencySim.class.getName());
    public static void main(String[] args) throws FileNotFoundException, IOException, FileReadException, InterruptedException, ControllerException
    {
        boolean cont = true;
        // Check if file name is included in the arguments
        if (args.length < 1) {
            System.out.println("File Name Missing! Usage : ./gradlew run --args=<file_name>");
        }else {
            // Simulation timer
            long time = 0;
            FileRead file = new FileRead(args[0]);
            ResponderCommImpl responder = new ResponderCommImpl();
            Controller control = new Controller(responder);
            ControllerHandler handler = new ControllerHandler(file, control);
            // Read file data
            file.readFile();
            logger.info("File Read Successful!");
            logger.info("Simulation Started");
            // Do while loop to simulate each second
            do {
                System.out.print("\033[H\033[2J");
                System.out.println("...:: Emgergency Simulation ::...");
                System.out.print("\t  Time : ["+ time + "](s)\n");
                // set the current time in the controller
                control.setTime(time);
                // check if the rescuers have arrived/departed
                if (!handler.attended()) {
                    cont = false;
                }
                // simulate the emergencies
                handler.simulate();
                time++;
                Thread.sleep(1000);
            } while (cont);
            System.out.print("\nTime Reached : Ending Simulation!\n");
            logger.info("Simulation Ended");
        }
    }
}