package pl.coderslab.sports_bets_webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_bets_webapp.entity.Event;
import pl.coderslab.sports_bets_webapp.entity.enums.BetTypeEnum;
import pl.coderslab.sports_bets_webapp.model.EventForViewModel;
import pl.coderslab.sports_bets_webapp.service.BetService;
import pl.coderslab.sports_bets_webapp.service.EventForViewService;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventForViewServiceImpl implements EventForViewService {

    @Autowired
    BetService betService;

    @Override
    public List<EventForViewModel> convert(List<Event> events, BetTypeEnum betType) {

        List<EventForViewModel> eventsForView =new ArrayList<>();
        for (Event event: events){
           eventsForView.add(
                   new EventForViewModel
                           (event, betService.getEventBetsInArrayByType(event, betType)));
        }
    return eventsForView;
    }
}
