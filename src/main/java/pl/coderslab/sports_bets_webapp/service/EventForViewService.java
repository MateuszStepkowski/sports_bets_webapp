package pl.coderslab.sports_bets_webapp.service;

import pl.coderslab.sports_bets_webapp.entity.Event;
import pl.coderslab.sports_bets_webapp.entity.enums.BetTypeEnum;
import pl.coderslab.sports_bets_webapp.model.EventForViewModel;

import java.util.List;

public interface EventForViewService {

    List<EventForViewModel> convert(List<Event> events, BetTypeEnum betType);
}
