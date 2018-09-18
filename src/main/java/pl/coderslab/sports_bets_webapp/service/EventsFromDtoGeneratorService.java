package pl.coderslab.sports_bets_webapp.service;

import pl.coderslab.sports_bets_webapp.dto.EventDto;
import pl.coderslab.sports_bets_webapp.entity.Event;

public interface EventsFromDtoGeneratorService {

    Event createAndSaveNewEvent(EventDto eventDto);

    Event updateEvent(EventDto eventDto);

}
