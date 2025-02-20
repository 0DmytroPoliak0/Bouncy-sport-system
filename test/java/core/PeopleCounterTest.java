package core;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PeopleCounterTest {
    private PeopleCounter peopleCounter;

    @BeforeEach
    void setUp() {
        peopleCounter = new PeopleCounter(5);
    }

    @Test
    @DisplayName("Test initial count is zero")
    void testInitialCount() {
        assertEquals(0, peopleCounter.getCurrentCount(), "Initial count should be 0");
    }

    @Test
    @DisplayName("Test incrementCount increases current count")
    void testIncrementCount() {
        peopleCounter.incrementCount();
        assertEquals(1, peopleCounter.getCurrentCount(), "Count should be 1 after one increment");
    }

    @Test
    @DisplayName("Test decrementCount decreases current count")
    void testDecrementCount() {
        peopleCounter.incrementCount();
        peopleCounter.decrementCount();
        assertEquals(0, peopleCounter.getCurrentCount(), "Count should return to 0 after decrement");
    }

    @Test
    @DisplayName("Test increment does not exceed max capacity")
    void testMaxCapacity() {
        for (int i = 0; i < 6; i++) {
            peopleCounter.incrementCount();
        }
        assertEquals(5, peopleCounter.getCurrentCount(), "Count should not exceed max capacity of 5");
    }

    @Test
    @DisplayName("Test decrement does not go below zero")
    void testMinCount() {
        peopleCounter.decrementCount();
        assertEquals(0, peopleCounter.getCurrentCount(), "Count should not go below zero");
    }

    @Test
    @DisplayName("Test setMaxCapacity updates max capacity")
    void testSetMaxCapacity() {
        peopleCounter.setMaxCapacity(10);
        assertEquals(10, peopleCounter.getMaxCapacity(), "Max capacity should be updated to 10");
    }
}


