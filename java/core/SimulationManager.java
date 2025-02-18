package core;

import core.state.DefaultState;
import core.state.SimulationState;
import management.StoreManager;
import authentication.StorePersonnel;

import java.util.ArrayList;
import java.util.List;

public class SimulationManager {
    private PeopleCounter peopleCounter;
    private Barrier entryBarrier;
    private Barrier exitBarrier;
    private Sensor entrySensor;
    private Sensor exitSensor;
    private StoreManager storeManager;
    private SimulationState currentState;

    private List<StorePersonnel> storePersonnelList;

    public SimulationManager(int maxCapacity) {
        peopleCounter = new PeopleCounter(maxCapacity);
        entryBarrier = new Barrier();
        exitBarrier = new Barrier();
        entrySensor = new Sensor("entry", peopleCounter);
        exitSensor = new Sensor("exit", peopleCounter);
        storePersonnelList = new ArrayList<>();

        // Adding default store personnel
        storePersonnelList.add(new StorePersonnel("VALID_CARD_1"));
        storePersonnelList.add(new StorePersonnel("VALID_CARD_2"));

        storeManager = new StoreManager(peopleCounter, storePersonnelList);

        // Observer pattern for barrier updates
        peopleCounter.addObserver(entryBarrier);
        peopleCounter.addObserver(exitBarrier);

        currentState = new DefaultState();
    }

    public List<StorePersonnel> getStorePersonnelList() {
        return storePersonnelList;
    }

    public StoreManager getStoreManager() {
        return storeManager;
    }

    public void startSimulation() {
        while (true) {
            currentState.handleInput(this);
        }
    }

    public Barrier getEntryBarrier() {
        return entryBarrier;
    }

    public Barrier getExitBarrier() {
        return exitBarrier;
    }

    public Sensor getEntrySensor() {
        return entrySensor;
    }

    public Sensor getExitSensor() {
        return exitSensor;
    }

    public SimulationState getState() {
        return currentState;
    }

    public void setState(SimulationState newState) {
        currentState = newState;
    }

    public SimulationState getDefaultState() {
        return new DefaultState();
    }
}
