package pl.coderslab.sports_bets_webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.sports_bets_webapp.entity.Bet;
import pl.coderslab.sports_bets_webapp.entity.Event;
import pl.coderslab.sports_bets_webapp.entity.enums.BetStatusEnum;
import pl.coderslab.sports_bets_webapp.entity.enums.BetTypeEnum;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface BetRepository extends JpaRepository<Bet, Integer> {

    Bet findFirstByEventAndBetType(Event event, BetTypeEnum betType);

    List<Bet> findAllByEventAndBetType(Event event, BetTypeEnum betType);

    List<Bet> findAllByBetStatusAndEventEndDate(BetStatusEnum betStatus, Timestamp endDate);

    @Query("SELECT b FROM Bet b JOIN Coupon_Bet c_b WHERE c_b.coupon_id =?1")
    List<Bet> findAllBycouponID(int id);
}
