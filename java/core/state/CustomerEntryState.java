package core.state;

import core.SimulationManager;

public class CustomerEntryState implements SimulationState {
    @Override
    public void handleInput(SimulationManager context) {
        context.getEntryBarrier().openBarrier();
        context.getEntrySensor().detectEntry();
        context.getEntryBarrier().closeBarrier();
        context.setState(context.getDefaultState()); // Return to default state
    }
}
