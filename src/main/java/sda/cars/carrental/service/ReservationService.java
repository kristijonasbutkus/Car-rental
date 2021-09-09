package sda.cars.carrental.service;

import sda.cars.carrental.entity.Reservations;

import java.util.List;

public interface ReservationService {

    List<Reservations> findAll();
    Reservations findById(long theId);
    void save(Reservations theReservation);
    void deleteById(long theId);

}
