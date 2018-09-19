package pl.coderslab.sports_bets_webapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.sports_bets_webapp.entity.League;
import pl.coderslab.sports_bets_webapp.entity.Team;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

    List<Team> findAllByLeaguesContains(League league);

    Team findFirstByLeaguesContainsAndName(League league, String name);
}
