package edu.curtin.emergencysim.file;

import edu.curtin.emergencysim.control.types.ChemicalEmg;
import edu.curtin.emergencysim.control.types.FireEmg;
import edu.curtin.emergencysim.control.types.FloodEmg;

/* *******************************************************************
* File:       DisasterData.java
* Author:     G.G.T.Shashen
* Created:    26/06/2022
* Modified:   2/06/2022
* Desc:       Disaster Data class implmentation
***********************************************************************/
public class DisasterData implements Comparable<DisasterData> {
    private long time;
    private long arrivedTime;
    private String type;
    private String location;
    private boolean start = false;
    private boolean end = false;
    private boolean attended = false;
    private FireEmg fire;
    private FloodEmg flood;
    private ChemicalEmg chem;

    public DisasterData(long time, String type, String location) {
        this.time = time;
        this.type = type;
        this.location = location;
        // create a new emergency type for each new emergency
        if (this.type.equals("fire")) {
            fire = new FireEmg();
        } else if (this.type.equals("flood")) {
            flood = new FloodEmg();
        } else if (this.type.equals("chemical")) {
            chem = new ChemicalEmg();
        }
    }

    // get start time of the emergency
    public long getTime() {
        return time;
    }

    // get the location of the emergency
    public String getLocation() {
        return location;
    }

    // get the type of the emergency
    public String getType() {
        return type;
    }

    // set boolean start for emergency
    public void setStart(boolean start) {
        this.start = start;
    }

    // return if the emergency has started
    public boolean hasStart() {
        return start;
    }

    // set boolean end for emergency
    public void setEnd() {
        end = true;
    }

    // return if the emergency has ended
    public boolean hasEnd() {
        return end;
    }

    // get the current data fire type
    public FireEmg getFire() {
        return fire;
    }

    // get the current data flood type
    public FloodEmg getFlood() {
        return flood;
    }

    // get the current data chemical type
    public ChemicalEmg getChem() {
        return chem;
    }

    // return if the rescuers have attended the emergencpy
    public boolean isAttended() {
        return attended;
    }

    // set the emergency as attended
    public void setAttended(boolean attended) {
        this.attended = attended;
    }

    // set the arrived time of the rescuers
    public void setArrivedTime(long time) {
        arrivedTime = time;
    }

    // get the arrived time of the rescuers
    public long getArrivedTime() {
        return arrivedTime;
    }

    // override method of Comparable interface to compare object data
    @Override
    public int compareTo(DisasterData data) {
        Long compareTime = this.time - data.getTime();
        return compareTime.intValue();
    }
}
