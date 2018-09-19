package pl.coderslab.sports_bets_webapp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_bets_webapp.entity.Event;
import pl.coderslab.sports_bets_webapp.entity.League;
import pl.coderslab.sports_bets_webapp.entity.Team;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findAllByStartDateBeforeAndEndDateIsNull(LocalDateTime startDate);

    Event findFirstByStartDateAndTeamAAndTeamBAnAndLeague(LocalDateTime startDate, Team teamA, Team teamB, League league);

    Event findFirstByStartDateAndTeamAAndTeamBAndLeagueAndEndDateIsNull(LocalDateTime startDate, Team teamA, Team teamB, League league);


}
