package sda.cars.carrental.service;

import sda.cars.carrental.entity.Cars;

import java.util.List;

public interface CarService {

    List<Cars> findAll();
    Cars findById(long theId);
    void save(Cars theCar);
    void deleteById(long theId);
}
