package pl.coderslab.sports_bets_webapp.service.impl;

import org.springframework.stereotype.Service;
import pl.coderslab.sports_bets_webapp.entity.Event;
import pl.coderslab.sports_bets_webapp.service.OddsGeneratorService;

import java.math.BigDecimal;

@Service
public class OddsGeneratorServiceImpl implements OddsGeneratorService {

    private final BigDecimal margine = BigDecimal.valueOf(0.05);




    /**
     * @param event
     * @return BigDecimal[3] array = { teamA_win_odds , draw_odds , teamB_win_odds}
     */
    @Override
    public BigDecimal[] updateOddsForEvent(Event event) {

        BigDecimal[] updatedOdds = generateOddsForEvent(event);

        //when time is running out, timeFactor is increasing
        BigDecimal timeFactor = BigDecimal.valueOf(event.getLeague().getSport().getGame_duration())
                .divide((BigDecimal.valueOf(event.getLeague().getSport().getGame_duration()-event.getLive_duration_time())));



        //when diffrence increasing, differenceFactor increasing
        BigDecimal scoreDifferenceFactor =
                (BigDecimal.valueOf(event.getLeague().getSport().getPoints_value())
                        .add(BigDecimal.valueOf(event.getTeamA_pts()-event.getTeamB_pts()).abs())
                )
                .divide(BigDecimal.valueOf(event.getLeague().getSport().getPoints_value()));


        BigDecimal finalFactor = timeFactor.multiply(scoreDifferenceFactor);


        BigDecimal teamApower = BigDecimal.valueOf(event.getTeamA().getOffensiveRating() + event.getTeamA().getDeffensiveRating());
        BigDecimal teamBpower = BigDecimal.valueOf(event.getTeamB().getOffensiveRating() + event.getTeamB().getDeffensiveRating());
        BigDecimal drawPower;

        if (teamApower.compareTo(teamBpower) > 0) {
            drawPower = (teamApower.subtract(teamBpower)).divide(BigDecimal.valueOf(2)).add(teamBpower);
        } else {
            drawPower = (teamBpower.subtract(teamApower)).divide(BigDecimal.valueOf(2)).add(teamApower);
        }


        if (event.getTeamA_pts() > event.getTeamB_pts()){
            teamApower = teamBpower.add(teamApower.multiply((finalFactor.divide(BigDecimal.valueOf(10)))));
        } else if (event.getTeamA_pts() < event.getTeamB_pts()) {
            teamBpower = teamBpower.add(teamBpower.multiply((finalFactor.divide(BigDecimal.valueOf(10)))));
        }else {
            drawPower = drawPower.add(drawPower.multiply((finalFactor.divide(BigDecimal.valueOf(10)))));
        }
        



        BigDecimal wholePower = teamApower.add(teamBpower).add(drawPower);

        //

        BigDecimal teamAodds = (wholePower)
                .divide(teamApower.multiply(BigDecimal.valueOf(1).subtract(margine)));

        BigDecimal teamBodds = (wholePower)
                .divide(teamBpower.multiply(BigDecimal.valueOf(1).subtract(margine)));

        BigDecimal drawOdds = (wholePower)
                .divide(drawPower.multiply(BigDecimal.valueOf(1).subtract(margine)));

        BigDecimal[] result = {teamAodds,drawOdds,teamBodds};

        return result;




    }

    /**
     * @param event
     * @return BigDecimal[3] array = { teamA_win_odds , draw_odds , teamB_win_odds}
     */
    @Override
    public BigDecimal[] generateOddsForEvent(Event event) {



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

        BigDecimal[] result = {teamAodds,drawOdds,teamBodds};

        return result;
    }
}
