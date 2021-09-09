package sda.cars.carrental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sda.cars.carrental.entity.Cars;
import sda.cars.carrental.error.CustomException;
import sda.cars.carrental.error.RangeValidationException;
import sda.cars.carrental.repository.CarsJpaRepository;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    private CarsJpaRepository carJpaRepository;

    @Autowired
    public CarServiceImpl(CarsJpaRepository carJpaRepo) {
        carJpaRepository = carJpaRepo;
    }

    @Override
    public Cars findById(long theId) {

        Optional<Cars> result = carJpaRepository.findById(theId);
        Cars theCar;
        if (result.isPresent()){
            theCar = result.get();
        } else {
            throw new CustomException("Car ID=" + theId + " Not found");
        }
        return theCar;
    }

    @Override
    public List<Cars> findAll() {
        return carJpaRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Cars theCar) throws RangeValidationException {
        try {
            carJpaRepository.save(theCar);
        } catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                throw new RangeValidationException(e);
            }
        }
    }

    @Override
    @Transactional
    public void deleteById(long theId) {
        carJpaRepository.deleteById(theId);
    }


}
