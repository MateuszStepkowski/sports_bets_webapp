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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class BetsForEventServiceImpl implements BetsForEventService {

    private final BigDecimal margine = BigDecimal.valueOf(0.05);

    @Autowired
    BetService betService;

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


        BigDecimal teamApower = BigDecimal.valueOf(event.getTeamA().getOffensiveRating() + event.getTeamA().getDeffensiveRating());
        BigDecimal teamBpower = BigDecimal.valueOf(event.getTeamB().getOffensiveRating() + event.getTeamB().getDeffensiveRating());
        BigDecimal drawPower;

        if (teamApower.compareTo(teamBpower) > 0) {
            drawPower = (teamApower.subtract(teamBpower)).divide(BigDecimal.valueOf(2)).add(teamBpower);
        } else {
            drawPower = (teamBpower.subtract(teamApower)).divide(BigDecimal.valueOf(2)).add(teamApower);
        }

        BigDecimal wholePower = teamApower.add(teamBpower).add(drawPower);

        //

        BigDecimal teamAodds = (wholePower)
                .divide(teamApower.multiply(BigDecimal.valueOf(1).subtract(margine)));

        BigDecimal teamBodds = (wholePower)
                .divide(teamBpower.multiply(BigDecimal.valueOf(1).subtract(margine)));

        BigDecimal drawOdds = (wholePower)
                .divide(drawPower.multiply(BigDecimal.valueOf(1).subtract(margine)));


        betTeamA.setBetContent(BetContentEnum.TeamA);
        betTeamA.setActual_odds(teamAodds.setScale(2, RoundingMode.DOWN));

        betTeamB.setBetContent(BetContentEnum.TeamB);
        betTeamB.setActual_odds(teamBodds.setScale(2, RoundingMode.DOWN));

        betDraw.setBetContent(BetContentEnum.DRAW);
        betDraw.setActual_odds(drawOdds.setScale(2, RoundingMode.DOWN));

        betService.save(betTeamA);
        betService.save(betTeamB);
        betService.save(betDraw);

    }

    @Override
    public void generate_inPlay_Win_Lose_Draw(Event event) {



    }

    @Override
    public void updateInPlayBetsOddsForEvent(Event event) {

    }

    @Override
    public void makeBeforeGameBetsWaiting(Event event) {
        List<Bet> bets = betService.findAllBeforeGame(event);
        for (Bet bet : bets) {
            bet.setBetStatus(BetStatusEnum.WAITING);
        }

        betService.saveAll(bets);

    }

    @Override
    public void makeInPlayBetsWaiting(Event event) {

        List<Bet> bets = betService.findAllInPlay(event);

        for (Bet bet : bets) {
            bet.setBetStatus(BetStatusEnum.WAITING);
        }
    }


}
