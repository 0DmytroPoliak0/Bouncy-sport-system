package authentication;

public interface AuthenticationStrategy {
    boolean authenticate(String id);
}
