package pl.coderslab.sports_bets_webapp.model;

import org.springframework.security.core.GrantedAuthority;
import pl.coderslab.sports_bets_webapp.entity.User;

import java.util.Collection;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public CurrentUser(String login,
                       String password,
                       Collection<? extends GrantedAuthority> authorities,
                       User user) {
        super(login, password, authorities);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}