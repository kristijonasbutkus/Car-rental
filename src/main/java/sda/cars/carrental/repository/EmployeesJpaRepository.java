package sda.cars.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.cars.carrental.entity.Employees;

public interface EmployeesJpaRepository extends JpaRepository<Employees, Long> {
}
