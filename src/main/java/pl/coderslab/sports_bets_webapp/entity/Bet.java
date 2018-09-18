package pl.coderslab.sports_bets_webapp.entity;

import pl.coderslab.sports_bets_webapp.entity.enums.BetStatusEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotNull
    @ManyToOne
    private Event event;


    @OneToMany(mappedBy = "bet")
    private List<Coupon_Bet> coupon_bet = new ArrayList<>();

    @NotNull
    private BigDecimal actual_odds;

    @NotNull
    private BetStatusEnum betStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }



    public BigDecimal getActual_odds() {
        return actual_odds;
    }

    public List<Coupon_Bet> getCoupon_bet() {
        return coupon_bet;
    }

    public void setCoupon_bet(List<Coupon_Bet> coupon_bet) {
        this.coupon_bet = coupon_bet;
    }

    public void setActual_odds(BigDecimal actual_odds) {
        this.actual_odds = actual_odds;
    }

    public BetStatusEnum getBetStatus() {
        return betStatus;
    }

    public void setBetStatus(BetStatusEnum betStatus) {
        this.betStatus = betStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet bet = (Bet) o;
        return getId() == bet.getId();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Bet{" +
                "id=" + id +
                ", actual_odds=" + actual_odds +
                ", betStatus=" + betStatus +
                '}';
    }
}
