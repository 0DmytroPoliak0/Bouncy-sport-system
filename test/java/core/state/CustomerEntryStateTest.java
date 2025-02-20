package core.state;

import core.SimulationManager;
import core.Barrier;
import core.Sensor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerEntryStateTest {
    private SimulationManager simulationManager;
    private CustomerEntryState entryState;

    @BeforeEach
    void setUp() {
        simulationManager = new SimulationManager(5); // Max capacity 5
        entryState = new CustomerEntryState();
    }

    @Test
    @DisplayName("Test CustomerEntryState handles entry process correctly")
    void testHandleInput() {
        Barrier entryBarrier = simulationManager.getEntryBarrier();
        Sensor entrySensor = simulationManager.getEntrySensor();

        // Ensure barrier starts closed
        assertFalse(entryBarrier.isOpen(), "Barrier should start closed.");

        // Execute state behavior
        entryState.handleInput(simulationManager);

        // Barrier should be open, then closed
        assertFalse(entryBarrier.isOpen(), "Barrier should be closed after entry process.");

        // Verify state returns to DefaultState
        assertTrue(simulationManager.getState() instanceof DefaultState, "State should return to DefaultState.");
    }
}