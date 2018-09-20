package pl.coderslab.sports_bets_webapp.service;

import pl.coderslab.sports_bets_webapp.entity.User;

public interface UserService {

    User findByLogin(String login);

    void save(User user);

}
