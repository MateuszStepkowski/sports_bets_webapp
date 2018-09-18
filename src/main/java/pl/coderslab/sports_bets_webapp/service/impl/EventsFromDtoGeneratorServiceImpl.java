package pl.coderslab.sports_bets_webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_bets_webapp.dto.EventDto;
import pl.coderslab.sports_bets_webapp.entity.Event;
import pl.coderslab.sports_bets_webapp.service.EventService;
import pl.coderslab.sports_bets_webapp.service.EventsFromDtoGeneratorService;
import pl.coderslab.sports_bets_webapp.service.LeagueService;
import pl.coderslab.sports_bets_webapp.service.TeamService;

import java.time.LocalDateTime;

@Service
public class EventsFromDtoGeneratorServiceImpl implements EventsFromDtoGeneratorService {

    @Autowired
    EventService eventService;

    @Autowired
    LeagueService leagueService;

    @Autowired
    TeamService teamService;

    @Override
    public Event updateEvent(EventDto eventDto) {

        Event event = new Event();
        event.setStartDate(LocalDateTime.from(eventDto.getStartDate()));
        event.setLeague(leagueService.findByName(eventDto.getLeague()));
        event.setTeamA(teamService.findInLeagueByName(event.getLeague(), eventDto.getTeamA()));
        event.setTeamB(teamService.findInLeagueByName(event.getLeague(), eventDto.getTeamB()));
        event.setTeamA_pts(eventDto.getTeamA_pts());
        event.setTeamB_pts(eventDto.getTeamB_pts());
        event.setEndDate(LocalDateTime.from(eventDto.getEndDate()));

        Event eventFromDb = eventService.findBy_StartDate_TeamA_TeamB_League(event.getStartDate(),
                event.getTeamA(), event.getTeamB(), event.getLeague());

        /**protecting for creating new Event
         *  which is already In Play*/
        if (eventFromDb != null) {

            event.setId(eventFromDb.getId());
            return eventService.save(event);
        }


        return null;
    }

    @Override
    public Event createAndSaveNewEvent(EventDto eventDto) {

        Event event = new Event();
        event.setStartDate(LocalDateTime.from(eventDto.getStartDate()));
        event.setLeague(leagueService.findByName(eventDto.getLeague()));
        event.setTeamA(teamService.findInLeagueByName(event.getLeague(), eventDto.getTeamA()));
        event.setTeamB(teamService.findInLeagueByName(event.getLeague(), eventDto.getTeamB()));

        Event eventFromDb = eventService.
                findUnfinishedBy_StartDate_TeamA_TeamB_League(
                        event.getStartDate(), event.getTeamA(), event.getTeamB(), event.getLeague());

        /**protecting for participating one team
         *  in two events in the same time   */
        if (eventFromDb == null) {
            return eventService.save(event);
        }
        return null;
    }
}
