package sda.cars.carrental.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sda.cars.carrental.entity.Cars;
import sda.cars.carrental.entity.Users;
import sda.cars.carrental.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/car/api")
@Slf4j
public class CarsApiController {


    @Autowired
    private CarService carService;

    @GetMapping("/car-list")
    public List<Cars> showCarList() {
        return carService.findAll();
    }

    @GetMapping("/car/{id}")
    public Cars getCar(@PathVariable Long id){
        return carService.findById(id);
    }

    @PostMapping("/car/new")
    public Cars newCar (@RequestBody Cars newCar) {
        carService.save(newCar);
        return carService.findById(newCar.getId());
    }

    @PutMapping("/car/save/{id}")
    public Cars saveCar (@RequestBody Cars newCar) {
        carService.save(newCar);
        return carService.findById(newCar.getId());
    }

    @DeleteMapping("/car/delete/{id}")
    public boolean deleteCar(@PathVariable("id") Long id) {
        try {
            carService.deleteById(id);
            return true;
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
