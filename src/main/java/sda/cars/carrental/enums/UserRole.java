package sda.cars.carrental.enums;

public enum UserRole {
    USER,
    GUEST,
    ADMIN,
    ;

    @Override
    public String toString() {
        return name();
    }
}
