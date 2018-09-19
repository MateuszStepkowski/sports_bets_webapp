package pl.coderslab.sports_bets_webapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.sports_bets_webapp.entity.League;

@Repository
public interface LeagueRepository extends JpaRepository<League, Integer> {

    League findFirstByName(String name);
}
