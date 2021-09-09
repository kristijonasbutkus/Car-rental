package sda.cars.carrental.service;


import sda.cars.carrental.entity.Employees;

import java.util.List;

public interface EmployeeService {

    List<Employees> findAll();
    Employees findById(long theId);
    void save(Employees theEmployee);
    void deleteById(long theId);

}
