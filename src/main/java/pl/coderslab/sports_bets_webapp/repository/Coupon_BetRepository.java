package pl.coderslab.sports_bets_webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_bets_webapp.entity.Coupon_Bet;

public interface Coupon_BetRepository extends JpaRepository<Coupon_Bet, Integer> {
}
