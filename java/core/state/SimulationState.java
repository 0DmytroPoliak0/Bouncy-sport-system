package core.state;

import core.SimulationManager;

public interface SimulationState {
    void handleInput(SimulationManager context);
}
