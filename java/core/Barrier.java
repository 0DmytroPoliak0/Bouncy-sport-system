package core;

import java.util.Observer;
import java.util.Observable;

public class Barrier implements Observer {
    private boolean isOpen;

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof PeopleCounter) {
            boolean open = (boolean) arg;
            if (open) {
                openBarrier();
            } else {
                closeBarrier();
            }
        }
    }

    public void openBarrier() {
        isOpen = true;
        System.out.println("Barrier is now OPEN.");
    }

    public void closeBarrier() {
        isOpen = false;
        System.out.println("Barrier is now CLOSED.");
    }

    public boolean isOpen() {
        return isOpen;
    }
}
