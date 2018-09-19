package pl.coderslab.sports_bets_webapp.service;

import pl.coderslab.sports_bets_webapp.entity.Bet;
import pl.coderslab.sports_bets_webapp.entity.Event;

import java.util.List;

public interface BetService {

    Bet findFirstInPlayByEvent(Event event);

    List<Bet> findAllBeforeGame(Event event);

    List<Bet> findAllInPlay(Event event);

    void saveAll(List<Bet> bets);

    List<Bet> findAllWaitingWithEndedEvent();

    void checkAndSetWinOrLost(Bet bet);

    List<Bet> findAllByCouponId(int couponID);

    Bet save(Bet bet);
}
