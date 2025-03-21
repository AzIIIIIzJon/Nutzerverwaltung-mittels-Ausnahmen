import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RegistrationServiceCenter {
    private final Set<User> users = new HashSet<>();
    private int registrationCount = 0;

    public Set<User> getUsers() {
        return users;
    }

    public void registerUser(User user) throws UserAlreadyRegisteredException, NoRegistrationAllowedException, RegistrationInterruptedException {
        if (registrationCount >= 5) {
            throw new NoRegistrationAllowedException("Heute können keine Registrierungen mehr durchgeführt werden.");
        }

        if (!users.add(user)) {
            throw new UserAlreadyRegisteredException("Ein Nutzer mit diesem Namen ist bereits registriert.");
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RegistrationInterruptedException("Die Registrierung wurde vorzeitig abgebrochen.");
        }

        registrationCount++;
    }

    public void requestUserRegistration(User user) {
        try {
            registerUser(user);
        } catch (UserAlreadyRegisteredException e) {
            System.out.println("Ein Nutzer mit diesem Namen ist bereits registriert.");
        } catch (NoRegistrationAllowedException e) {
            System.out.println("Heute können keine Registrierungen mehr durchgeführt werden, bitte kommen Sie morgen wieder.");
        } catch (RegistrationInterruptedException e) {
            System.out.println("Die Registrierung wurde vorzeitig abgebrochen.");
        }
    }
}
