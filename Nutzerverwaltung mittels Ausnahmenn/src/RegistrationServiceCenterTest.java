import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistrationServiceCenterTest {
    private RegistrationServiceCenter serviceCenter;
    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        serviceCenter = new RegistrationServiceCenter();
        user1 = new User("Alice", "Berlin");
        user2 = new User("Bob", "Hamburg");
    }

    @Test
    public void testRegisterUser_success() throws Exception {
        serviceCenter.registerUser(user1);
        assertTrue(serviceCenter.getUsers().contains(user1));
    }

    @Test
    public void testRegisterUser_userAlreadyRegistered() {
        serviceCenter.requestUserRegistration(user1);
        Exception exception = assertThrows(UserAlreadyRegisteredException.class, () -> {
            serviceCenter.registerUser(user1);
        });
        assertEquals("Ein Nutzer mit diesem Namen ist bereits registriert.", exception.getMessage());
    }

    @Test
    public void testRegisterUser_noRegistrationAllowed() {
        for (int i = 0; i < 5; i++) {
            User user = new User("User" + i, "City" + i);
            serviceCenter.requestUserRegistration(user);
        }

        NoRegistrationAllowedException exception = assertThrows(NoRegistrationAllowedException.class, () -> {
            serviceCenter.registerUser(user1);
        });
        assertEquals("Heute können keine Registrierungen mehr durchgeführt werden.", exception.getMessage());
    }

    @Test
    public void testRegisterUser_registrationInterrupted() {
        Thread.currentThread().interrupt(); // Interrupt the current thread
        RegistrationInterruptedException exception = assertThrows(RegistrationInterruptedException.class, () -> {
            serviceCenter.registerUser(user1);
        });
        assertEquals("Die Registrierung wurde vorzeitig abgebrochen.", exception.getMessage());
    }

    @Test
    public void testRequestUserRegistration_userAlreadyRegistered() {
        serviceCenter.requestUserRegistration(user1);
        serviceCenter.requestUserRegistration(user1);
    }

    @Test
    public void testRequestUserRegistration_noRegistrationAllowed() {
        for (int i = 0; i < 5; i++) {
            User user = new User("User" + i, "City" + i);
            serviceCenter.requestUserRegistration(user);
        }

        serviceCenter.requestUserRegistration(user1);
    }
}
