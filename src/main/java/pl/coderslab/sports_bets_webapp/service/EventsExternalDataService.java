package pl.coderslab.sports_bets_webapp.service;


import pl.coderslab.sports_bets_webapp.dto.EventDto;

public interface EventsExternalDataService {

    void loadNewEvents(EventDto eventDto);

    void loadEventsUpdates(EventDto eventDto);

    void loadData();
}
