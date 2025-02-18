package core;

import java.util.Observable;

public class PeopleCounter extends Observable {
    private int currentCount = 0;
    private int maxCapacity;

    public PeopleCounter(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void incrementCount() {
        if (currentCount >= maxCapacity) {
            System.out.println("Store is at full capacity! Entry denied.");
            setChanged();
            notifyObservers(false); // Notify observers (e.g., Barrier) to close
        } else {
            currentCount++;
            System.out.println("Customer entered. Current count: " + currentCount);
            setChanged();
            notifyObservers(true); // Notify observers to open
        }
    }

    public void decrementCount() {
        if (currentCount > 0) {
            currentCount--;
            System.out.println("Customer exited. Current count: " + currentCount);
            setChanged();
            notifyObservers(true); // Notify observers to open
        } else {
            System.out.println("No customers in the store!");
        }
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        System.out.println("Maximum capacity updated to: " + maxCapacity);
    }
}
