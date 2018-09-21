package pl.coderslab.sports_bets_webapp.model;

import pl.coderslab.sports_bets_webapp.entity.Bet;
import pl.coderslab.sports_bets_webapp.entity.User;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CurrentCoupon {

    @NotNull
    private User user;

    private List<Bet> bets = new ArrayList<>();

    private BigDecimal totalOdds = BigDecimal.valueOf(1);

    public CurrentCoupon(@NotNull User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }

    public BigDecimal getTotalOdds() {
        return totalOdds;
    }

    public void setTotalOdds() {

        BigDecimal total = BigDecimal.valueOf(1);

        for (Bet bet : getBets()){
            total = total.multiply(bet.getActual_odds());
        }

        this.totalOdds = total;

    }
}
