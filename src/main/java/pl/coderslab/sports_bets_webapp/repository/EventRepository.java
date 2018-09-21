package pl.coderslab.sports_bets_webapp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.sports_bets_webapp.entity.Event;
import pl.coderslab.sports_bets_webapp.entity.League;
import pl.coderslab.sports_bets_webapp.entity.Team;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findAllByStartDateBeforeAndEndDateIsNull(Timestamp startDate);

    Event findFirstByStartDateAndTeamAAndTeamBAndLeague(Timestamp startDate, Team teamA, Team teamB, League league);

    Event findFirstByStartDateAndTeamAAndTeamBAndLeagueAndEndDateIsNull(Timestamp startDate, Team teamA, Team teamB, League league);

    List<Event> findTop5ByStartDateAfterOrderByStartDateAsc(Timestamp startDate);

    List<Event> findAllByLeagueSportNameAndStartDateBeforeAndEndDateIsNull(String sport, Timestamp startDate);

    List<Event> findAllByStartDateAfterAndLeagueSportName(Timestamp startDate, String sport);

    List<Event> findTop5ByStartDateBeforeAndEndDateIsNull(Timestamp startDate);
}
