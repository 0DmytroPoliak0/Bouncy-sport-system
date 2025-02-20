package core.state;

import core.SimulationManager;
import management.StoreManager;
import org.junit.jupiter.api.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ManagerMenuStateTest {
    private SimulationManager simulationManager;
    private ManagerMenuState managerMenuState;
    private StoreManager storeManager;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        simulationManager = new SimulationManager(5); // Store capacity: 5
        managerMenuState = new ManagerMenuState();
        storeManager = simulationManager.getStoreManager(); // Get real StoreManager instance

        // Capture console output
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void restoreSystemStreams() {
        System.setOut(System.out); // Restore normal output
        System.setIn(System.in); // Restore normal input
    }

    // good

    @Test
    @DisplayName("Test option 1: View customer count")
    void testViewCustomerCount() {
        simulateInput("1\n");
        managerMenuState.handleInput(simulationManager);

        String output = outputStream.toString();
        assertTrue(output.contains("Current customer count:"), "Should display customer count");
        assertTrue(simulationManager.getState() instanceof DefaultState, "Should return to DefaultState.");
    }

    // failed

    @Test
    @DisplayName("Test option 2: Update max capacity")
    void testUpdateMaxCapacity() throws NoSuchFieldException, IllegalAccessException {
        simulateInput("2\n10\n"); // Enter 10 as new max capacity
        managerMenuState.handleInput(simulationManager);

        // Check console output for confirmation message
        String output = outputStream.toString();
        assertTrue(output.contains("Enter new maximum capacity:"), "Should prompt for new capacity");
        assertTrue(output.contains("Maximum capacity updated"), "Should confirm max capacity update");

        // Get private `PeopleCounter` using reflection
        Field peopleCounterField = StoreManager.class.getDeclaredField("peopleCounter");
        peopleCounterField.setAccessible(true);
        Object peopleCounterObject = peopleCounterField.get(storeManager);

        // Extract `maxCapacity` from `PeopleCounter`
        Field maxCapacityField = peopleCounterObject.getClass().getDeclaredField("maxCapacity");
        maxCapacityField.setAccessible(true);
        int maxCapacity = maxCapacityField.getInt(peopleCounterObject);

        assertEquals(10, maxCapacity, "Max capacity should be updated to 10");
        assertTrue(simulationManager.getState() instanceof DefaultState, "Should return to DefaultState.");
    }

    // good

    @Test
    @DisplayName("Test option 3: List store personnel")
    void testListStorePersonnel() {
        simulateInput("3\n");
        managerMenuState.handleInput(simulationManager);

        // Check if personnel list is printed
        String output = outputStream.toString();
        assertTrue(output.contains("Store Personnel List") || output.contains("No store personnel registered"), "Should display store personnel list");
        assertTrue(simulationManager.getState() instanceof DefaultState, "Should return to DefaultState.");
    }

    // failed

    @Test
    @DisplayName("Test invalid input returns to DefaultState")
    void testInvalidInput() {
        simulateInput("99\n");
        managerMenuState.handleInput(simulationManager);

        String output = outputStream.toString();
        assertTrue(output.contains("Invalid option"), "Should print invalid option message");
        assertTrue(simulationManager.getState() instanceof DefaultState, "Should return to DefaultState.");
    }

    private void simulateInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }


}