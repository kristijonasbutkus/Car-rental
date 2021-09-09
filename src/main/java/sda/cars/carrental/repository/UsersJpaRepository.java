package sda.cars.carrental.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sda.cars.carrental.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UsersJpaRepository extends JpaRepository<Users, Long> {

    @Query(value = "SELECT u FROM Users u WHERE u.email = :email")
    public Users findByEmail(
            @Param("email") String email);
}
