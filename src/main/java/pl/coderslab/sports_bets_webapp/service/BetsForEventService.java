package pl.coderslab.sports_bets_webapp.service;

import pl.coderslab.sports_bets_webapp.entity.Event;

public interface BetsForEventService {

    void generate_beforeGame_Win_Lose_Draw(Event event);

    void generate_inPlay(Event event);

    void updateBetsForEvent(Event event);

}
