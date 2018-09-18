package pl.coderslab.sports_bets_webapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_bets_webapp.entity.Sport;

public interface SportRepository extends JpaRepository<Sport, Integer> {
}
