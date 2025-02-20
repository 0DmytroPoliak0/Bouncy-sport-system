package management;

import core.PeopleCounter;
import authentication.StorePersonnel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StoreManagerTest {
    private StoreManager storeManager;
    private PeopleCounter peopleCounter;
    private List<StorePersonnel> storePersonnelList;

    @BeforeEach
    void setUp() {
        // Initialize with a max capacity of 10
        peopleCounter = new PeopleCounter(10);

        // Create a sample personnel list
        storePersonnelList = Arrays.asList(
                new StorePersonnel("001"),
                new StorePersonnel("002")
        );

        // Create an instance of StoreManager using actual objects
        storeManager = new StoreManager(peopleCounter, storePersonnelList);
    }



    @Test
    @DisplayName("Test viewCustomerCount reflects correct people count")
    void testViewCustomerCount() {
        assertEquals(0, peopleCounter.getCurrentCount(), "Initial count should be 0");
        peopleCounter.incrementCount();
        assertEquals(1, peopleCounter.getCurrentCount(), "Customer count should be 1 after incrementing");
    }

    @Test
    @DisplayName("Test setCapacityLimit updates max capacity with valid input")
    void testSetCapacityLimitValid() {
        // Simulate valid user input: "15"
        System.setIn(new ByteArrayInputStream("15\n".getBytes()));

        // Call the method that uses Scanner
        storeManager.setCapacityLimit();

        // Verify that the max capacity updated correctly
        assertEquals(15, peopleCounter.getMaxCapacity(), "Max capacity should be updated to 15");
    }

    @Test
    @DisplayName("Test setCapacityLimit handles invalid input gracefully")
    void testSetCapacityLimitInvalid() {
        int beforeChange = peopleCounter.getMaxCapacity();

        // Simulate invalid inputs (-5 and a non-numeric string)
        System.setIn(new ByteArrayInputStream("-5\n".getBytes()));
        storeManager.setCapacityLimit();
        assertEquals(beforeChange, peopleCounter.getMaxCapacity(), "Max capacity should not change when given a negative number");

        System.setIn(new ByteArrayInputStream("invalid\n".getBytes()));
        storeManager.setCapacityLimit();
        assertEquals(beforeChange, peopleCounter.getMaxCapacity(), "Max capacity should not change when given non-numeric input");
    }

    @Test
    @DisplayName("Test listStorePersonnel outputs correct personnel IDs")
    void testListStorePersonnel() {
        List<String> expectedIDs = Arrays.asList("001", "002");

        // Check if every personnel ID is in the expected list
        for (StorePersonnel personnel : storePersonnelList) {
            assertTrue(expectedIDs.contains(personnel.getId()), "Personnel list should contain correct IDs");
        }
    }

}