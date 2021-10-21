package sda.cars.carrental.controller;

import sda.cars.carrental.enums.UserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sda.cars.carrental.entity.Users;
import sda.cars.carrental.service.UserService;


import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String ShowUsersList(Model model) {
        model.addAttribute("usersList", userService.findAll());
        return "users/user-list";
    }

    @GetMapping("/user/new")
    public String addNewUser(Model model) {
        Users users = new Users();
        model.addAttribute("newUser", users);
        return "users/user-add";
    }

    @GetMapping("/user/edit/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {
        Users tempUser = userService.findById(id);
        model.addAttribute("editUser", tempUser);
        return "users/user-edit";
    }

    @PostMapping ("/user/save")
    public String newUser(Users user) {
        Optional<Users> optionalUsers = userService.findOptionalByEmail(user.getEmail());
        if (!optionalUsers.isPresent()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
            return "users/user-list";
        }
        // TO DO: handle TemplateInputException!
        else return "Duplicate user email in the database.";
    }

    @PostMapping ("/user/save/{id}")
    public String updateUser(Users user, @PathVariable("id") Long id) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
            return "users/user-list";
    }

    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "users/user-list";
    }

}
