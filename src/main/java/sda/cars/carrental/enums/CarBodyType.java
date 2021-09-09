package sda.cars.carrental.enums;

import lombok.Getter;

public enum CarBodyType {
    SEDAN ("Sedan"),
    HATCHBACK ("Hatchback"),
    COUPE ("Coupe"),
    SUV ("Sports-Utility Vehicle"),
    PICKUP_TRUCK ("Pickup Truck");

    @Getter
    private String displayBodyTypes;

    CarBodyType(String displayBodyTypes) { this.displayBodyTypes = displayBodyTypes;
    }
}
