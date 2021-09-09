package sda.cars.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.cars.carrental.entity.Reservations;


public interface ReservationsJpaRepository extends JpaRepository<Reservations, Long> {
}
