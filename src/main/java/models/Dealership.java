package models;

import java.util.List;

public class Dealership {
    private List<Car> cars;

    public Dealership() {
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
