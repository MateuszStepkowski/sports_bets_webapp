package pl.coderslab.sports_bets_webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_bets_webapp.entity.Event;
import pl.coderslab.sports_bets_webapp.entity.League;
import pl.coderslab.sports_bets_webapp.entity.Team;
import pl.coderslab.sports_bets_webapp.repository.EventRepository;
import pl.coderslab.sports_bets_webapp.service.EventService;


import java.sql.Timestamp;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Override
    public List<Event> findAllInPlay() {
        return eventRepository.findAllByStartDateBeforeAndEndDateIsNull(new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public List<Event> findAllInPlay(String sport) {
        return eventRepository.findAllByLeagueSportNameAndStartDateBeforeAndEndDateIsNull(sport, new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public List<Event> findAllBeforeGame(String sport) {
        return eventRepository.findAllByStartDateAfterAndLeagueSportName(new Timestamp(System.currentTimeMillis()), sport);
    }

    @Override
    public void saveAll(List<Event> events) {

        eventRepository.saveAll(events);
    }

    @Override
    public Event save(Event event) {

        return eventRepository.save(event);
    }

    @Override
    public Event findBy_StartDate_TeamA_TeamB_League(Timestamp startDate, Team teamA, Team teamB, League league) {
        return eventRepository.findFirstByStartDateAndTeamAAndTeamBAndLeague(startDate,teamA,teamB, league);
    }

    @Override
    public Event findUnfinishedBy_StartDate_TeamA_TeamB_League(Timestamp startDate, Team teamA, Team teamB, League league) {
        return eventRepository.findFirstByStartDateAndTeamAAndTeamBAndLeagueAndEndDateIsNull(startDate, teamA, teamB, league);
    }

    @Override
    public List<Event> findFirst5NearestNotStarted() {
        return eventRepository.findTop5ByStartDateAfterOrderByStartDateAsc(new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public List<Event> findFirst5InPlay() {
        return eventRepository.findTop5ByStartDateBeforeAndEndDateIsNull(new Timestamp(System.currentTimeMillis()));
    }

}
