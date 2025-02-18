package core.state;

import core.SimulationManager;
import authentication.StorePersonnel;
import authentication.CardAuthentication;

import java.util.Scanner;

public class DefaultState implements SimulationState {
    @Override
    public void handleInput(SimulationManager context) {
        System.out.println("Options:");
        System.out.println("1. Simulate customer entry");
        System.out.println("2. Simulate customer exit");
        System.out.println("3. Manager menu");
        System.out.println("4. Simulate store personnel entry/exit");
        System.out.println("5. Quit");


        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        switch (input) {
            case "1":
                context.setState(new CustomerEntryState());
                break;
            case "2":
                context.setState(new CustomerExitState());
                break;
            case "3":
                context.setState(new ManagerMenuState());
                break;
            case "4":
                System.out.print("Enter personnel card ID: ");
                String id = scanner.nextLine();
                StorePersonnel personnel = new StorePersonnel(id);
                boolean authenticated = personnel.authenticate(new CardAuthentication());
                if (authenticated) {
                    System.out.println("Store personnel authenticated. Do you want to [1] Enter or [2] Exit?");
                    String action = scanner.nextLine();
                    if ("1".equals(action)) {
                        System.out.println("Personnel entered through the barrier.");
                    } else if ("2".equals(action)) {
                        System.out.println("Personnel exited through the barrier.");
                    } else {
                        System.out.println("Invalid action.");
                    }
                }
                break;
            case "5":
                System.out.println("Exiting simulation.");
                System.exit(0);
            default:
                System.out.println("Invalid option.");
        }

        context.getState().handleInput(context); // Pass control to the new state
    }
}
