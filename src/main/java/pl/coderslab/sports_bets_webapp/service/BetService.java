package pl.coderslab.sports_bets_webapp.service;

import pl.coderslab.sports_bets_webapp.entity.Bet;
import pl.coderslab.sports_bets_webapp.entity.Coupon;
import pl.coderslab.sports_bets_webapp.entity.Event;
import pl.coderslab.sports_bets_webapp.entity.enums.BetTypeEnum;

import java.util.List;

public interface BetService {

    Bet findFirstInPlayByEvent(Event event);

    List<Bet> findAllBeforeGame(Event event);

    List<Bet> findAllInPlay(Event event);

    void saveAll(List<Bet> bets);

    List<Bet> findAllWaitingWithEndedEvent();

    void checkAndSetWinOrLost(Bet bet);

    List<Bet> findAllByCoupon(Coupon coupon);

    Bet save(Bet bet);

    Bet[] getEventBetsInArrayByType(Event event,BetTypeEnum betType);

    Bet findByID(int ID);

}
