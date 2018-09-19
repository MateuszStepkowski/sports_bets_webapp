package pl.coderslab.sports_bets_webapp.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_bets_webapp.dto.EventDto;
import pl.coderslab.sports_bets_webapp.entity.Event;
import pl.coderslab.sports_bets_webapp.jms.JmsSubscriber;
import pl.coderslab.sports_bets_webapp.service.BetsForEventService;
import pl.coderslab.sports_bets_webapp.service.EventService;
import pl.coderslab.sports_bets_webapp.service.EventsExternalDataService;

import java.io.IOException;

/**
 * Receive External Data in Json,
 * make new events from it and create allowed bets for created event
 */

@Service
public class EventsExternalDataServiceImpl implements EventsExternalDataService {


    @Autowired
    JmsSubscriber jmsSubscriber;

    @Autowired
    EventsFromDtoGeneratorServiceImpl eventsFromDtoGenerator;

    @Autowired
    BetsForEventService betsForEventService;

    @Autowired
    EventService eventService;

    private final String newEventsQueue = "new_events.t";
    private final String eventsUpdateQueue = "events_update.t";

    @Override
    @Async
    public void loadNewEvents() {

        while (true) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                EventDto newEventDto = mapper.readValue(jmsSubscriber.receive(newEventsQueue), EventDto.class);
                Event event = eventsFromDtoGenerator.createAndSaveNewEvent(newEventDto);
                if (event != null){
                    betsForEventService.generate_beforeGame_Win_Lose_Draw(event);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @Async
    public void loadEventsUpdates() {

        while (true) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                EventDto newEventDto = mapper.readValue(jmsSubscriber.receive(eventsUpdateQueue), EventDto.class);
                Event event = eventsFromDtoGenerator.generateEventToUpdate(newEventDto);
                if (event.getEndDate() != null){
                    betsForEventService.makeInPlayBetsWaiting(event);
                }
                eventService.save(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
