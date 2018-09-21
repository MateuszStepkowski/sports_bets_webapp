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
@RequestMapping("/bets/basketball")
public class BasketBallController {

    @Autowired
    EventService eventService;

    @Autowired
    EventForViewService eventForViewService;

    @GetMapping("/")
    public String displayBasketBall(Model model){

        List<EventForViewModel> basketballInPlay =
                eventForViewService.convert(eventService.findAllInPlay("basketball"), BetTypeEnum.IN_PLAY);

        List<EventForViewModel> basketballBeforeGame =
                eventForViewService.convert(eventService.findAllBeforeGame("basketball"), BetTypeEnum.BEFORE_GAME);

        model.addAttribute("inPlay", basketballInPlay);
        model.addAttribute("beforeGame", basketballBeforeGame);

        return "basketball";
    }
}