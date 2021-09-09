package sda.cars.carrental.service;

import sda.cars.carrental.entity.Users;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<Users> findAll();
    Users findById(long theId);
    void save(Users theUser);
    void deleteById(long Id);
    public Users findByEmail(String email);
    public Optional<Users> findOptionalByEmail(String email);

}
