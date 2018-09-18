package pl.coderslab.sports_bets_webapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_bets_webapp.entity.League;
import pl.coderslab.sports_bets_webapp.entity.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {

    List<Team> findAllByLeaguesContains(League league);
}
