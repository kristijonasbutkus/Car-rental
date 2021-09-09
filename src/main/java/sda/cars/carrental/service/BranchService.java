package sda.cars.carrental.service;


import sda.cars.carrental.entity.Branches;

import java.util.List;

public interface BranchService {

    List<Branches> findAll();
    Branches findById(long theId);
    void save(Branches theUser);
    void deleteById(long theId);


}
