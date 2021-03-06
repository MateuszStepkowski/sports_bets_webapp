package pl.coderslab.sports_bets_webapp.service;



import pl.coderslab.sports_bets_webapp.entity.Event;
import pl.coderslab.sports_bets_webapp.entity.League;
import pl.coderslab.sports_bets_webapp.entity.Team;

import java.sql.Timestamp;
import java.util.List;

public interface EventService {

    List<Event> findAllInPlay();

    List<Event> findAllInPlay(String sport);

    List<Event> findAllBeforeGame(String sport);

    void saveAll(List<Event> events);

    Event save(Event event);

    Event findBy_StartDate_TeamA_TeamB_League(Timestamp startDate, Team teamA, Team teamB, League league);

    Event findUnfinishedBy_StartDate_TeamA_TeamB_League(Timestamp startDate, Team teamA, Team teamB, League league);

    List<Event> findFirst5NearestNotStarted();

    List<Event> findFirst5InPlay();
}
