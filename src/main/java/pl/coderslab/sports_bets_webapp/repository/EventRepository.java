package pl.coderslab.sports_bets_webapp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.sports_bets_webapp.entity.Event;
import pl.coderslab.sports_bets_webapp.entity.League;
import pl.coderslab.sports_bets_webapp.entity.Team;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findAllByStartDateBeforeAndEndDateIsNull(LocalDateTime startDate);

    Event findFirstByStartDateAndTeamAAndTeamBAnAndLeague(LocalDateTime startDate, Team teamA, Team teamB, League league);

    Event findFirstByStartDateAndTeamAAndTeamBAndLeagueAndEndDateIsNull(LocalDateTime startDate, Team teamA, Team teamB, League league);


}
