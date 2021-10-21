package sda.cars.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;
import sda.cars.carrental.entity.Cars;
import sda.cars.carrental.entity.Users;
import sda.cars.carrental.service.CarService;
import sda.cars.carrental.utilities.FileUploadUtil;

import java.io.IOException;

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

    @GetMapping("/car/new")
    public String addCar (Model model) {
        Cars cars = new Cars();
        model.addAttribute("newCar", cars);
        return "cars/car-add";
    }

    @PostMapping("/car/save")
    public RedirectView saveUser(Cars cars, @RequestParam("photo") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        cars.setPhoto(fileName);

        carService.save(cars);

        String uploadDir = "src/main/resources/images/cars/" + cars.getId();

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return new RedirectView("/car/{id}", true);
    }



}
