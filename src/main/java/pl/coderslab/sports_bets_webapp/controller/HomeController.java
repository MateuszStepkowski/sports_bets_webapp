package pl.coderslab.sports_bets_webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.sports_bets_webapp.entity.enums.BetTypeEnum;
import pl.coderslab.sports_bets_webapp.model.EventForViewModel;
import pl.coderslab.sports_bets_webapp.service.EventForViewService;
import pl.coderslab.sports_bets_webapp.service.EventService;

import java.util.List;

@Controller
@RequestMapping(path = {"/", "/home"})
public class HomeController {

    @Autowired
    EventService eventService;

    @Autowired
    EventForViewService eventForViewService;

    @GetMapping
    public String homePage(Model model){

        List<EventForViewModel> upcoming =
                eventForViewService.convert(eventService.findFirst5NearestNotStarted(), BetTypeEnum.BEFORE_GAME);

        List<EventForViewModel> inPlay =
                eventForViewService.convert(eventService.findFirst5NearestNotStarted(), BetTypeEnum.IN_PLAY);


        model.addAttribute("beforeGame", upcoming);
        model.addAttribute("inPlay", inPlay);
        return "homePage";
    }
}
