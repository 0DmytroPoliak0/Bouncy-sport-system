package core.state;

import core.SimulationManager;

public class CustomerExitState implements SimulationState {
    @Override
    public void handleInput(SimulationManager context) {
        context.getExitBarrier().openBarrier();
        context.getExitSensor().detectExit();
        context.getExitBarrier().closeBarrier();
        context.setState(context.getDefaultState()); // Return to default state
    }
}
