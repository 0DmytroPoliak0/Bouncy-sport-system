package core.state;

import core.SimulationManager;

import java.util.Scanner;

public class ManagerMenuState implements SimulationState {
    @Override
    public void handleInput(SimulationManager context) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Manager Menu:");
        System.out.println("1. View current customer count");
        System.out.println("2. Update max capacity");
        System.out.println("3. List store personnel");
        System.out.println("4. Return to main menu");

        String input = scanner.nextLine();
        switch (input) {
            case "1":
                context.getStoreManager().viewCustomerCount();
                break;
            case "2":
                context.getStoreManager().setCapacityLimit();
                break;
            case "3":
                context.getStoreManager().listStorePersonnel();
                break;
            default:
                System.out.println("Returning to main menu.");
        }
        context.setState(context.getDefaultState()); // Return to default state
    }
}
