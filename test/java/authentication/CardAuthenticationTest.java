package authentication;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardAuthenticationTest {

    private final CardAuthentication cardAuth = new CardAuthentication();

    @Test
    @DisplayName("Should return true for a valid card ID")
    void testValidCardID() {
        assertTrue(cardAuth.authenticate("VALID_CARD_1"), "Expected authentication to pass for VALID_CARD_1");
        assertTrue(cardAuth.authenticate("VALID_CARD_2"), "Expected authentication to pass for VALID_CARD_2");
    }

    @Test
    @DisplayName("Should return false for an invalid card ID")
    void testInvalidCardID() {
        assertFalse(cardAuth.authenticate("INVALID_CARD"), "Expected authentication to fail for INVALID_CARD");
    }

    @Test
    @DisplayName("Should return false for null input")
    void testNullInput() {
        assertFalse(cardAuth.authenticate(null), "Expected authentication to fail for null input");
    }

    @Test
    @DisplayName("Should return false for an empty string")
    void testEmptyString() {
        assertFalse(cardAuth.authenticate(""), "Expected authentication to fail for empty string");
    }




}