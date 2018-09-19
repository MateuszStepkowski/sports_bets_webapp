package pl.coderslab.sports_bets_webapp.service;

import pl.coderslab.sports_bets_webapp.entity.Event;

public interface BetsForEventService {

    void generate_beforeGame_Win_Lose_Draw(Event event);

    void generate_inPlay_Win_Lose_Draw(Event event);

    void updateInPlayBetsOddsForEvent(Event event);

    void makeBeforeGameBetsWaiting(Event event);

    void makeInPlayBetsWaiting(Event event);

}
