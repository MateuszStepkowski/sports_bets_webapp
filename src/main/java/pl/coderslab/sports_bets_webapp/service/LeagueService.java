package pl.coderslab.sports_bets_webapp.service;


import pl.coderslab.sports_bets_webapp.entity.League;

import java.util.List;

public interface LeagueService {

    List<League> findAll();

    League findByName(String name);
}