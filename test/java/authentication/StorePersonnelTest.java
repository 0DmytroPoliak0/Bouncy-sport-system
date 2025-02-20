package authentication;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("StorePersonnel Authentication Tests")
class StorePersonnelTest {

    private StorePersonnel personnel;
    private TestAuthenticationStrategy successStrategy;
    private TestAuthenticationStrategy failureStrategy;

    @BeforeEach
    void setUp() {
        personnel = new StorePersonnel("12345"); // Create StorePersonnel instance
        successStrategy = new TestAuthenticationStrategy(true);  // Always returns true
        failureStrategy = new TestAuthenticationStrategy(false); // Always returns false
    }

    @Test
    @DisplayName("Authentication should succeed with a valid strategy")
    void testAuthenticationSuccess() {
        assertTrue(personnel.authenticate(successStrategy),
                "Expected authentication to succeed, but it failed.");
    }

    @Test
    @DisplayName("Authentication should fail with an invalid strategy")
    void testAuthenticationFailure() {
        assertFalse(personnel.authenticate(failureStrategy),
                "Expected authentication to fail, but it succeeded.");
    }

    @Test
    @DisplayName("Should return correct ID")
    void testGetId() {
        assertEquals("12345", personnel.getId(),
                "Expected ID to be '12345', but got something else.");
    }

    @RepeatedTest(3)
    @DisplayName("Repeated test: Authentication should work consistently")
    void repeatedTestAuthentication() {
        assertTrue(personnel.authenticate(successStrategy),
                "Expected authentication to consistently succeed.");
    }

    @AfterEach
    void tearDown() {
        // Clean up if necessary (not really needed here, but good habit)
        personnel = null;
    }

    // Custom implementation of AuthenticationStrategy for testing.
    private static class TestAuthenticationStrategy implements AuthenticationStrategy {
        private final boolean shouldAuthenticate;

        TestAuthenticationStrategy(boolean shouldAuthenticate) {
            this.shouldAuthenticate = shouldAuthenticate;
        }

        @Override
        public boolean authenticate(String id) {
            return shouldAuthenticate;
        }
    }
}