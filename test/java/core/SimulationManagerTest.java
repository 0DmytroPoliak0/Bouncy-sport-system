package core;

import core.state.DefaultState;
import core.state.SimulationState;
import management.StoreManager;
import authentication.StorePersonnel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationManagerTest {
    private SimulationManager simulationManager;

    @BeforeEach
    void setUp() {
        simulationManager = new SimulationManager(5); // Max capacity 5
    }

    @Test
    @DisplayName("Test SimulationManager initializes all components correctly")
    void testInitialization() {
        assertNotNull(simulationManager.getStoreManager(), "StoreManager should be initialized.");
        assertNotNull(simulationManager.getEntryBarrier(), "Entry Barrier should be initialized.");
        assertNotNull(simulationManager.getExitBarrier(), "Exit Barrier should be initialized.");
        assertNotNull(simulationManager.getEntrySensor(), "Entry Sensor should be initialized.");
        assertNotNull(simulationManager.getExitSensor(), "Exit Sensor should be initialized.");
        assertNotNull(simulationManager.getState(), "State should be initialized.");
    }

    @Test
    @DisplayName("Test store personnel are correctly initialized")
    void testStorePersonnelInitialization() {
        List<StorePersonnel> personnelList = simulationManager.getStorePersonnelList();
        assertEquals(2, personnelList.size(), "Should have 2 store personnel initialized.");
        assertEquals("VALID_CARD_1", personnelList.get(0).getId(), "First personnel ID should be VALID_CARD_1.");
        assertEquals("VALID_CARD_2", personnelList.get(1).getId(), "Second personnel ID should be VALID_CARD_2.");
    }

    @Test
    @DisplayName("Test setState updates current state correctly")
    void testSetState() {
        SimulationState newState = new DefaultState();
        simulationManager.setState(newState);
        assertEquals(newState, simulationManager.getState(), "State should be updated correctly.");
    }





}