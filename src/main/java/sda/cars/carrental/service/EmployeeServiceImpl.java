package sda.cars.carrental.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.cars.carrental.entity.Employees;
import sda.cars.carrental.error.CustomException;
import sda.cars.carrental.repository.EmployeesJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeesJpaRepository employeeJpaRepository;

    public EmployeeServiceImpl(EmployeesJpaRepository employeeJpaRepo) {
        this.employeeJpaRepository = employeeJpaRepo;
    }

    @Override
    public Employees findById(long theId) {

        Optional<Employees> result = employeeJpaRepository.findById(theId);
        Employees theEmployee;
        if (result.isPresent()) {
            theEmployee = result.get();
        } else {
            throw new CustomException("Employee with ID=" + theId + " Not found");
        }
        return theEmployee;
    }

    @Override
    public List<Employees> findAll() {
        return employeeJpaRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Employees theEmployee) {
        employeeJpaRepository.save(theEmployee);
    }

    @Override
    @Transactional
    public void deleteById(long theId) {
        employeeJpaRepository.deleteById(theId);
    }
}
