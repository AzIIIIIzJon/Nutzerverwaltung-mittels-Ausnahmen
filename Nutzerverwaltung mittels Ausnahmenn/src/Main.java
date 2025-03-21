public class Main {
    public static void main(String[] args) {
        RegistrationServiceCenter serviceCenter = new RegistrationServiceCenter();

        User user1 = new User("Alice", "Berlin");
        User user2 = new User("Bob", "Hamburg");
        User user3 = new User("Alice", "München"); // Gleicher Name wie user1

        serviceCenter.requestUserRegistration(user1);
        serviceCenter.requestUserRegistration(user2);
        serviceCenter.requestUserRegistration(user3); // Sollte UserAlreadyRegisteredException auslösen

        for (int i = 0; i < 5; i++) {
            User user = new User("User" + i, "City" + i);
            serviceCenter.requestUserRegistration(user);
        }

        // Dies sollte NoRegistrationAllowedException auslösen
        User user6 = new User("User6", "City6");
        serviceCenter.requestUserRegistration(user6);
    }
}
