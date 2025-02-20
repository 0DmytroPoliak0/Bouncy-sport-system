package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BarrierTest {
    private Barrier barrier;
    private PeopleCounter peopleCounter;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        barrier = new Barrier();
        peopleCounter = new PeopleCounter(3); // Max capacity is 3
        peopleCounter.addObserver(barrier); // Attach Barrier as an observer

        // Capture System.out output
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    //good

    @Test
    @DisplayName("Test barrier opens when space is available")
    void testBarrierOpens() {
        peopleCounter.incrementCount(); // Notify observers (barrier)
        assertTrue(barrier.isOpen(), "Barrier should be open after incrementing count.");
        assertTrue(outputStream.toString().contains("Barrier is now OPEN."),
                "Output should indicate that the barrier is open.");
    }

    //failed

    @Test
    @DisplayName("Test barrier closes when store is full")
    void testBarrierCloses() {
        peopleCounter.incrementCount();
        peopleCounter.incrementCount();
        peopleCounter.incrementCount(); // Max capacity reached

        assertFalse(barrier.isOpen(), "Barrier should be closed at max capacity.");
        assertTrue(outputStream.toString().contains("Barrier is now CLOSED."),
                "Output should indicate that the barrier is closed.");
    }

    //failed

    @Test
    @DisplayName("Test barrier reopens when space is available")
    void testBarrierReopens() {
        // Fill the store (closes barrier)
        peopleCounter.incrementCount();
        peopleCounter.incrementCount();
        peopleCounter.incrementCount();
        assertFalse(barrier.isOpen(), "Barrier should be closed at max capacity.");

        // Customer leaves (should open barrier)
        outputStream.reset();
        peopleCounter.decrementCount();
        assertTrue(barrier.isOpen(), "Barrier should be open again after decrement.");
        assertTrue(outputStream.toString().contains("Barrier is now OPEN."),
                "Output should indicate that the barrier reopened.");
    }



}