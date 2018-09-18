package pl.coderslab.sports_bets_webapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_bets_webapp.entity.League;

public interface LeagueRepository extends JpaRepository<League, Integer> {

    League findFirstByName(String name);
}
