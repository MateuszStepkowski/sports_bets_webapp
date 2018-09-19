package pl.coderslab.sports_bets_webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_bets_webapp.entity.Bet;
import pl.coderslab.sports_bets_webapp.entity.Event;
import pl.coderslab.sports_bets_webapp.entity.enums.BetStatusEnum;
import pl.coderslab.sports_bets_webapp.service.BetService;
import pl.coderslab.sports_bets_webapp.service.BetsForEventService;

import java.util.List;

@Service
public class BetsForEventServiceImpl implements BetsForEventService {

    @Autowired
    BetService betService;

    @Override
    public void generate_beforeGame_Win_Lose_Draw(Event event) {

    }

    @Override
    public void generate_inPlay(Event event) {

    }

    @Override
    public void updateInPlayBetsOddsForEvent(Event event) {

    }

    @Override
    public void makeBeforeGameBetsWaiting(Event event) {
        List<Bet> bets = betService.findAllBeforeGame(event);
        for (Bet bet : bets){
            bet.setBetStatus(BetStatusEnum.WAITING);
        }

        betService.saveAll(bets);

    }

    @Override
    public void makeInPlayBetsWaiting(Event event) {

        List<Bet> bets = betService.findAllInPlay(event);

        for (Bet bet : bets){
            bet.setBetStatus(BetStatusEnum.WAITING);
        }
    }




}
