package pl.coderslab.sports_bets_webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_bets_webapp.entity.Event;
import pl.coderslab.sports_bets_webapp.entity.League;
import pl.coderslab.sports_bets_webapp.entity.Team;
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
    public Event save(Event event) {

        return eventRepository.saveAndFlush(event);
    }

    @Override
    public Event findBy_StartDate_TeamA_TeamB_League(LocalDateTime startDate, Team teamA, Team teamB, League league) {
        return eventRepository.findFirstByStartDateAndTeamAAndTeamBAnAndLeague(startDate,teamA,teamB, league);
    }

    @Override
    public Event findUnfinishedBy_StartDate_TeamA_TeamB_League(LocalDateTime startDate, Team teamA, Team teamB, League league) {
        return eventRepository.findFirstByStartDateAndTeamAAndTeamBAndLeagueAndEndDateIsNull(startDate, teamA, teamB, league);
    }

}
