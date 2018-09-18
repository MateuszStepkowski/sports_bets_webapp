package pl.coderslab.sports_bets_webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_bets_webapp.entity.Event;
import pl.coderslab.sports_bets_webapp.repository.EventRepository;
import pl.coderslab.sports_bets_webapp.service.EventService;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Override
    public List<Event> findAllInPlay() {
        return eventRepository.findAllByStartDateBeforeAndEndDateIsNull(LocalDateTime.now());
    }

    @Override
    public void saveAll(List<Event> events) {

        eventRepository.saveAll(events);
    }

    @Override
    public void save(Event event) {

        eventRepository.saveAndFlush(event);
    }

}
