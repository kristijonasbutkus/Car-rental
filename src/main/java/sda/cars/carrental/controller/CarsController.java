package sda.cars.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sda.cars.carrental.entity.Cars;
import sda.cars.carrental.entity.Users;
import sda.cars.carrental.service.CarService;

@Controller
public class CarsController {

    @Autowired
    private CarService carService;

    @GetMapping("/car")
    public String ShowUsersList(Model model) {
        model.addAttribute("carsList", carService.findAll());
        return "cars/car-list";
    }

    @GetMapping("/car/{id}")
    public String getCar (Model model, @PathVariable Long id){
        model.addAttribute("car", carService.findById(id));
        return "cars/car-view";
    }

}
