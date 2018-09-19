package pl.coderslab.sports_bets_webapp.service;

import pl.coderslab.sports_bets_webapp.entity.Event;

import java.math.BigDecimal;

public interface OddsGeneratorService {


    BigDecimal[] updateOddsForEvent(Event event);

    BigDecimal[] generateOddsForEvent(Event event);
}
