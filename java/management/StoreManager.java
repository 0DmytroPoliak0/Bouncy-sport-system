package management;

import core.PeopleCounter;
import authentication.StorePersonnel;

import java.util.List;
import java.util.Scanner;

public class StoreManager {
    private final PeopleCounter peopleCounter;
    private final List<StorePersonnel> storePersonnelList;

    // Constructor to initialize the manager with a PeopleCounter and personnel list
    public StoreManager(PeopleCounter peopleCounter, List<StorePersonnel> storePersonnelList) {
        this.peopleCounter = peopleCounter;
        this.storePersonnelList = storePersonnelList;
    }

    // View the current customer count in the store
    public void viewCustomerCount() {
        System.out.println("Current customer count: " + peopleCounter.getCurrentCount());
    }

    // Update the maximum capacity of the store
    public void setCapacityLimit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new maximum capacity: ");
        try {
            int newCapacity = Integer.parseInt(scanner.nextLine());
            if (newCapacity > 0) {
                peopleCounter.setMaxCapacity(newCapacity);
                System.out.println("Maximum capacity updated to: " + newCapacity);
            } else {
                System.out.println("Capacity must be a positive number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a numeric value.");
        }
    }

    // List all store personnel IDs
    public void listStorePersonnel() {
        if (storePersonnelList.isEmpty()) {
            System.out.println("No store personnel registered.");
        } else {
            System.out.println("Store Personnel List:");
            for (StorePersonnel personnel : storePersonnelList) {
                System.out.println("- ID: " + personnel.getId());
            }
        }
    }
}
