package pl.coderslab.sports_bets_webapp.service;

import pl.coderslab.sports_bets_webapp.entity.Event;

public interface OddsGeneratorService {


    void updateOddsForEvent(Event event);

    void generateOddsForEventBeforeGame(Event event);
}
