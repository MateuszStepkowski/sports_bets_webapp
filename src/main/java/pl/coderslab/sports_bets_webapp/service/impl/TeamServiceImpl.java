package pl.coderslab.sports_bets_webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_bets_webapp.entity.League;
import pl.coderslab.sports_bets_webapp.entity.Team;
import pl.coderslab.sports_bets_webapp.repository.TeamRepository;
import pl.coderslab.sports_bets_webapp.service.TeamService;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Override
    public List<Team> findAllInLeague(League league) {
        return teamRepository.findAllByLeaguesContains(league);
    }

    @Override
    public Team findInLeagueByName(League league, String name) {
        return teamRepository.findFirstByLeaguesContainsAndName(league, name);
    }
}
