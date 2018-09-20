package pl.coderslab.sports_bets_webapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {



    @GetMapping("/login")
    public String loginUser(){
        return "login";
    }
}