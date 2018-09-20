package pl.coderslab.sports_bets_webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.sports_bets_webapp.service.EventService;

@Controller
@RequestMapping(path = {"/", "/home"})
public class HomeController {

    @Autowired
    EventService eventService;

    @GetMapping
    public String homePage(Model model){

        model.addAttribute("upcomingEvents", eventService.findFirst8NearestNotStarted());

        return "homePage";
    }
}
