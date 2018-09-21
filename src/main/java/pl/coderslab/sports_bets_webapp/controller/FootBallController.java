package pl.coderslab.sports_bets_webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.sports_bets_webapp.entity.enums.BetTypeEnum;
import pl.coderslab.sports_bets_webapp.model.EventForViewModel;
import pl.coderslab.sports_bets_webapp.service.EventForViewService;
import pl.coderslab.sports_bets_webapp.service.EventService;

import java.util.List;

@Controller
@RequestMapping("/bets/football")
public class FootBallController {

    @Autowired
    EventService eventService;

    @Autowired
    EventForViewService eventForViewService;

    @GetMapping("/")
    public String displayFootBall(Model model){

        List<EventForViewModel> footballInPlay =
                eventForViewService.convert(eventService.findAllInPlay("football"), BetTypeEnum.IN_PLAY);

        List<EventForViewModel> footballBeforeGame =
                eventForViewService.convert(eventService.findAllBeforeGame("football"), BetTypeEnum.BEFORE_GAME);

        model.addAttribute("inPlay", footballInPlay);
        model.addAttribute("beforeGame", footballBeforeGame);

        return "football";
    }
}
