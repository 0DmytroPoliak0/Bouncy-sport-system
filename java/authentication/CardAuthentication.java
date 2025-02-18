package authentication;

import java.util.Arrays;
import java.util.List;

public class CardAuthentication implements AuthenticationStrategy {
    // List of valid card IDs
    private static final List<String> VALID_IDS = Arrays.asList("VALID_CARD_1", "VALID_CARD_2");

    @Override
    public boolean authenticate(String id) {
        // Check if the provided ID exists in the valid IDs list
        return VALID_IDS.contains(id);
    }
}
