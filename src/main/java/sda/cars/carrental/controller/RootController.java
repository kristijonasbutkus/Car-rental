package sda.cars.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sda.cars.carrental.entity.Users;
import sda.cars.carrental.service.UserService;

@Controller
public class RootController {

    @GetMapping("/")
    public String ShowHomePage() {
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user_signup", new Users());
        return "main/signup-form";
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("user_login", new Users());
        return "main/login";
    }

}
