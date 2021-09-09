package sda.cars.carrental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sda.cars.carrental.entity.Branches;

public interface BranchesJpaRepository extends JpaRepository<Branches, Long> {

}
