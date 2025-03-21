import java.util.Objects;

public class User {
    private final String name;
    private final String address;

    public User(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object O) {
        if (this == O) {
            return true;
        }
        if (O == null || getClass() != O.getClass()) {
            return false;
        }
        User other = (User) O;
        return name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
