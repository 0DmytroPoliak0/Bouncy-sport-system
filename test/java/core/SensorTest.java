package core;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SensorTest {
    private Sensor sensor;
    private PeopleCounter peopleCounter;

    @BeforeEach
    void setup() {
        peopleCounter = new PeopleCounter(5);
        sensor = new Sensor("Entrance", peopleCounter);
    }

    @Test
    @DisplayName("Test detectEntry increases count")
    void testDetectEntry() {
        sensor.detectEntry();
        assertEquals(1, peopleCounter.getCurrentCount()); // Corrected method
    }

    @Test
    @DisplayName("Test detectExit decreases count")
    void testDetectExit() {
        sensor.detectEntry(); // First, someone enters
        sensor.detectExit(); // Then exits
        assertEquals(0, peopleCounter.getCurrentCount()); // Corrected method
    }
}