package pl.coderslab.sports_bets_webapp.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_bets_webapp.dto.EventDto;
import pl.coderslab.sports_bets_webapp.entity.Event;
import pl.coderslab.sports_bets_webapp.jms.JmsSubscriber;
import pl.coderslab.sports_bets_webapp.service.BetsForEventService;
import pl.coderslab.sports_bets_webapp.service.EventService;
import pl.coderslab.sports_bets_webapp.service.EventsExternalDataService;

import java.io.IOException;
import java.util.TimeZone;

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

    static int counter = 0;

    private final String eventsDataQueue = "events_data.t";

    @Override
    public void loadNewEvents(EventDto eventDto) {

        System.out.println("new events");

        Event event = eventsFromDtoGenerator.createAndSaveNewEvent(eventDto);


        if (event != null) {
            betsForEventService.generate_beforeGame_Win_Lose_Draw(event);
        }
    }

    @Override
    public void loadEventsUpdates(EventDto eventDto) {
        System.out.println("update events");

            Event event = eventsFromDtoGenerator.generateEventToUpdate(eventDto);
            if (event != null) {
                if (event.getEndDate() != null) {
                    betsForEventService.makeInPlayBetsWaiting(event);
                }
                eventService.save(event);
            }
    }

    @Override
    @Scheduled(fixedRate = 1)
    public void loadData() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getTimeZone("Europe/Warsaw"));
        try {
            EventDto newEventDto = mapper.readValue(jmsSubscriber.receive(eventsDataQueue), EventDto.class);

            if (newEventDto.getDataType().equals("NEW")){
                loadNewEvents(newEventDto);
            }else {
                loadEventsUpdates(newEventDto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
