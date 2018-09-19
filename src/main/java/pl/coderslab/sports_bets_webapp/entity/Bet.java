package pl.coderslab.sports_bets_webapp.entity;

import pl.coderslab.sports_bets_webapp.entity.enums.BetContentEnum;
import pl.coderslab.sports_bets_webapp.entity.enums.BetStatusEnum;
import pl.coderslab.sports_bets_webapp.entity.enums.BetTypeEnum;

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

    @NotNull
    private BetTypeEnum betType;

    @NotNull
    private BetContentEnum betContent;

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

    public BetTypeEnum getBetType() {
        return betType;
    }

    public void setBetType(BetTypeEnum betType) {
        this.betType = betType;
    }

    public BetContentEnum getBetContent() {
        return betContent;
    }

    public void setBetContent(BetContentEnum betContent) {
        this.betContent = betContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet bet = (Bet) o;
        return getId() == bet.getId() &&
                getBetType() == bet.getBetType();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getBetType());
    }

    @Override
    public String toString() {
        return "Bet{" +
                "id=" + id +
                ", event=" + event +
                ", actual_odds=" + actual_odds +
                ", betStatus=" + betStatus +
                ", betType=" + betType +
                '}';
    }
}
