package pl.coderslab.sports_bets_webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_bets_webapp.entity.Bet;
import pl.coderslab.sports_bets_webapp.entity.Event;
import pl.coderslab.sports_bets_webapp.entity.Team;
import pl.coderslab.sports_bets_webapp.entity.enums.BetContentEnum;
import pl.coderslab.sports_bets_webapp.entity.enums.BetStatusEnum;
import pl.coderslab.sports_bets_webapp.entity.enums.BetTypeEnum;
import pl.coderslab.sports_bets_webapp.service.BetService;
import pl.coderslab.sports_bets_webapp.service.BetsForEventService;
import pl.coderslab.sports_bets_webapp.service.OddsGeneratorService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class BetsForEventServiceImpl implements BetsForEventService {

    @Autowired
    BetService betService;

    @Autowired
    OddsGeneratorService oddsGeneratorService;

    @Override
    public void generate_beforeGame_Win_Lose_Draw(Event event) {


        Bet betTeamA = new Bet();
        Bet betTeamB = new Bet();
        Bet betDraw = new Bet();

        betTeamA.setEvent(event);
        betTeamB.setEvent(event);
        betDraw.setEvent(event);

        betTeamA.setBetType(BetTypeEnum.BEFORE_GAME);
        betTeamB.setBetType(BetTypeEnum.BEFORE_GAME);
        betDraw.setBetType(BetTypeEnum.BEFORE_GAME);

        betTeamA.setBetStatus(BetStatusEnum.ALLOWED);
        betTeamB.setBetStatus(BetStatusEnum.ALLOWED);
        betDraw.setBetStatus(BetStatusEnum.ALLOWED);


        BigDecimal[] odds = oddsGeneratorService.generateOddsForEvent(event);


        betTeamA.setBetContent(BetContentEnum.TeamA);
        betTeamA.setActual_odds(odds[0].setScale(2, RoundingMode.DOWN));

        betDraw.setBetContent(BetContentEnum.DRAW);
        betDraw.setActual_odds(odds[1].setScale(2, RoundingMode.DOWN));

        betTeamB.setBetContent(BetContentEnum.TeamB);
        betTeamB.setActual_odds(odds[2].setScale(2, RoundingMode.DOWN));


        betService.save(betTeamA);
        betService.save(betTeamB);
        betService.save(betDraw);

    }

    @Override
    public void generate_inPlay_Win_Lose_Draw(Event event) {


        Bet betTeamA = new Bet();
        Bet betTeamB = new Bet();
        Bet betDraw = new Bet();

        betTeamA.setEvent(event);
        betTeamB.setEvent(event);
        betDraw.setEvent(event);

        betTeamA.setBetType(BetTypeEnum.IN_PLAY);
        betTeamB.setBetType(BetTypeEnum.IN_PLAY);
        betDraw.setBetType(BetTypeEnum.IN_PLAY);

        betTeamA.setBetStatus(BetStatusEnum.ALLOWED);
        betTeamB.setBetStatus(BetStatusEnum.ALLOWED);
        betDraw.setBetStatus(BetStatusEnum.ALLOWED);


        BigDecimal[] odds = oddsGeneratorService.generateOddsForEvent(event);


        betTeamA.setBetContent(BetContentEnum.TeamA);
        betTeamA.setActual_odds(odds[0].setScale(2, RoundingMode.DOWN));

        betDraw.setBetContent(BetContentEnum.DRAW);
        betDraw.setActual_odds(odds[1].setScale(2, RoundingMode.DOWN));

        betTeamB.setBetContent(BetContentEnum.TeamB);
        betTeamB.setActual_odds(odds[2].setScale(2, RoundingMode.DOWN));


        betService.save(betTeamA);
        betService.save(betTeamB);
        betService.save(betDraw);

    }

    @Override
    public void updateInPlayBetsOddsForEvent(Event event) {

        List<Bet> bets = betService.findAllInPlay(event);

        BigDecimal[] updatedOdds = oddsGeneratorService.updateOddsForEvent(event);

        for (Bet bet : bets) {
            if (bet.getBetContent().equals(BetContentEnum.TeamA)) {
                bet.setActual_odds(updatedOdds[0].setScale(2, RoundingMode.DOWN));
            } else if (bet.getBetContent().equals(BetContentEnum.DRAW)) {
                bet.setActual_odds(updatedOdds[1].setScale(2, RoundingMode.DOWN));
            } else if (bet.getBetContent().equals(BetContentEnum.TeamB)) {
                bet.setActual_odds(updatedOdds[2].setScale(2, RoundingMode.DOWN));
            }
        }
        betService.saveAll(bets);
    }

        @Override
        public void makeBeforeGameBetsWaiting (Event event){
            List<Bet> bets = betService.findAllBeforeGame(event);
            for (Bet bet : bets) {
                bet.setBetStatus(BetStatusEnum.WAITING);
            }

            betService.saveAll(bets);

        }

        @Override
        public void makeInPlayBetsWaiting (Event event){

            List<Bet> bets = betService.findAllInPlay(event);

            for (Bet bet : bets) {
                bet.setBetStatus(BetStatusEnum.WAITING);
            }
            betService.saveAll(bets);
        }
    }
