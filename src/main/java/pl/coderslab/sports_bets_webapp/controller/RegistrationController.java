package pl.coderslab.sports_bets_webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.sports_bets_webapp.entity.User;
import pl.coderslab.sports_bets_webapp.service.UserService;

import javax.validation.Valid;


@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registerUser(Model model) {

        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@Valid User user, BindingResult result) {

        if (result.hasErrors()) {
            return "registration";
        }
        userService.save(user);

        return "redirect:/login";
    }
}