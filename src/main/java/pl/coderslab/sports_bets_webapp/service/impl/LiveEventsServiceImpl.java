package pl.coderslab.sports_bets_webapp.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import pl.coderslab.sports_bets_webapp.entity.Event;
import pl.coderslab.sports_bets_webapp.service.BetService;
import pl.coderslab.sports_bets_webapp.service.BetsForEventService;
import pl.coderslab.sports_bets_webapp.service.EventService;
import pl.coderslab.sports_bets_webapp.service.LiveEventsService;

import java.util.List;

@Service
public class LiveEventsServiceImpl implements LiveEventsService {

    @Autowired
    EventService eventService;

    @Autowired
    BetService betService;

    @Autowired
    BetsForEventService betsForEventService;


    @Override
    @Async
    public void createInPlayBets_OR_Updated_Odds() {

        while (true) {
            List<Event> liveEvents = eventService.findAllInPlay();

            for (Event event : liveEvents) {

                if (betService.findFirstInPlayByEvent(event) == null) {
                    betsForEventService.makeBeforeGameBetsWaiting(event);
                    betsForEventService.generate_inPlay_Win_Lose_Draw(event);
                } else {
                    betsForEventService.updateInPlayBetsOddsForEvent(event);
                }

            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
