package pl.coderslab.sports_bets_webapp.service;



import pl.coderslab.sports_bets_webapp.entity.Event;

import java.util.List;

public interface EventService {

    List<Event> findAllInPlay();

    void saveAll(List<Event> events);

    void save(Event event);

}
