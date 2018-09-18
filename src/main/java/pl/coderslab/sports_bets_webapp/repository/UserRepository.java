package pl.coderslab.sports_bets_webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_bets_webapp.entity.User;

public interface UserRepository  extends JpaRepository<User, Integer> {

    User findFirstByLogin(String login);
}
