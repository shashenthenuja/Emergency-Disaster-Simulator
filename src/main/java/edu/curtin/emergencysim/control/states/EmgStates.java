package edu.curtin.emergencysim.control.states;

import edu.curtin.emergencysim.file.DisasterData;

// State interface
public interface EmgStates {
    public void lowIntensity(DisasterData data);

    public void highIntensity(DisasterData data);

    public void start(DisasterData data);

    public void end(DisasterData data);

}
