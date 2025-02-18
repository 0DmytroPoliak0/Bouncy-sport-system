package core;

public class Sensor {
    private String location;
    private PeopleCounter peopleCounter;

    public Sensor(String location, PeopleCounter peopleCounter) {
        this.location = location;
        this.peopleCounter = peopleCounter;
    }

    public void detectEntry() {
        System.out.println("Entry detected at " + location + " sensor.");
        peopleCounter.incrementCount();
    }

    public void detectExit() {
        System.out.println("Exit detected at " + location + " sensor.");
        peopleCounter.decrementCount();
    }
}
