package core.state;

import core.SimulationManager;
import authentication.CardAuthentication;
import authentication.StorePersonnel;
import org.junit.jupiter.api.*;
import java.io.ByteArrayInputStream;
import static org.junit.jupiter.api.Assertions.*;

class DefaultStateTest {

    //tests failed

    private SimulationManager simulationManager;
    private DefaultState defaultState;

    @BeforeEach
    void setUp() {
        simulationManager = new SimulationManager(5); // Store capacity: 5
        defaultState = new DefaultState();
    }

    @Test
    @DisplayName("Test transition to CustomerEntryState")
    void testCustomerEntryTransition() {
        simulateInput("1\n"); // Simulate "1" as input
        defaultState.handleInput(simulationManager);
        assertTrue(simulationManager.getState() instanceof CustomerEntryState, "Should transition to CustomerEntryState.");
    }

    @Test
    @DisplayName("Test transition to CustomerExitState")
    void testCustomerExitTransition() {
        simulateInput("2\n"); // Simulate "2" as input
        defaultState.handleInput(simulationManager);
        assertTrue(simulationManager.getState() instanceof CustomerExitState, "Should transition to CustomerExitState.");
    }

    @Test
    @DisplayName("Test transition to ManagerMenuState")
    void testManagerMenuTransition() {
        simulateInput("3\n"); // Simulate "3" as input
        defaultState.handleInput(simulationManager);
        assertTrue(simulationManager.getState() instanceof ManagerMenuState, "Should transition to ManagerMenuState.");
    }

    @Test
    @DisplayName("Test invalid input does not change state")
    void testInvalidInput() {
        simulateInput("99\n"); // Simulate invalid input "99"
        defaultState.handleInput(simulationManager);
        assertTrue(simulationManager.getState() instanceof DefaultState, "Should remain in DefaultState for invalid input.");
    }

    // Simulates user input for testing purposes.
    //  Redirects System.in to a custom input stream.
    private void simulateInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
}