package core.state;
import core.SimulationManager;
import core.Barrier;
import core.Sensor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerExitStateTest {
    private SimulationManager simulationManager;
    private CustomerExitState exitState;

    @BeforeEach
    void setUp() {
        simulationManager = new SimulationManager(5); // Max capacity 5
        exitState = new CustomerExitState();
    }

    @Test
    @DisplayName("Test CustomerExitState handles exit process correctly")
    void testHandleInput() {
        Barrier exitBarrier = simulationManager.getExitBarrier();
        Sensor exitSensor = simulationManager.getExitSensor();

        // Ensure barrier starts closed
        assertFalse(exitBarrier.isOpen(), "Exit barrier should start closed.");

        // Execute state behavior
        exitState.handleInput(simulationManager);

        // Barrier should be open, then closed
        assertFalse(exitBarrier.isOpen(), "Exit barrier should be closed after exit process.");

        // Verify state returns to DefaultState
        assertTrue(simulationManager.getState() instanceof DefaultState, "State should return to DefaultState.");
    }
}