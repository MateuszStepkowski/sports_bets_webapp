package pl.coderslab.sports_bets_webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.coderslab.sports_bets_webapp.entity.Bet;
import pl.coderslab.sports_bets_webapp.entity.Coupon;
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

    @Query(value="SELECT b.* FROM bets b JOIN coupons_bets cb on cb.bet_id = b.id Join coupons c on c.id=cb_coupon_id WHERE c.id = :id", nativeQuery = true)
    List<Bet> findAllBycoupon(@Param("id") int id );

    Bet findFirstById(int id);
}
