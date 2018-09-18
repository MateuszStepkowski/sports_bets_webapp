package pl.coderslab.sports_bets_webapp.service;


import pl.coderslab.sports_bets_webapp.entity.League;
import pl.coderslab.sports_bets_webapp.entity.Team;

import java.util.List;

public interface TeamService {

    List<Team> findAllInLeague(League league);

    Team findInLeagueByName(League league, String name);
}
