package authentication;

public class StorePersonnel {
    private final String id;

    public StorePersonnel(String id) {
        this.id = id;
    }

    public boolean authenticate(AuthenticationStrategy strategy) {
        if (strategy.authenticate(id)) {
            System.out.println("Authentication successful. Access granted.");
            return true;
        } else {
            System.out.println("Authentication failed. Access denied.");
            return false;
        }
    }

    public String getId() {
        return id;
    }
}
