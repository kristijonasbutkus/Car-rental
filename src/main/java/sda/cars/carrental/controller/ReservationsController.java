package sda.cars.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sda.cars.carrental.entity.Reservations;
import sda.cars.carrental.service.BranchService;
import sda.cars.carrental.service.ReservationService;
import sda.cars.carrental.service.UserService;

import java.util.List;

@Controller
public class ReservationsController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @GetMapping("/reservations")
    public String ShowUsersList(Model model) {
        List<Reservations> reservations = reservationService.findAll();
        if (!reservationService.findAll().isEmpty()) {
            model.addAttribute("reservationList", reservationService.findAll());
            return "reservations/reservation-view";
        }
        else return "You have not made any reservations yet !";
    }

    @GetMapping("/reservations/{id}")
    public Reservations getReservation (@PathVariable Long id){
        return reservationService.findById(id);
    }

    @PostMapping("/reservations/new")
    public Reservations newReservation (@RequestBody Reservations newReservation) {
        reservationService.save(newReservation);
        return  reservationService.findById(newReservation.getId());
    }
}
