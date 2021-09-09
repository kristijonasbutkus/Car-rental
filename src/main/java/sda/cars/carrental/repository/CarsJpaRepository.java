package sda.cars.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.cars.carrental.entity.Cars;

public interface CarsJpaRepository extends JpaRepository<Cars, Long> {

}
