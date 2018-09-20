package pl.coderslab.sports_bets_webapp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "coupons_bets")
public class Coupon_Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne
    private Coupon coupon;

    @NotNull
    @ManyToOne
    private Bet bet;

    @NotNull
    private BigDecimal approved_bet_odds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public BigDecimal getApproved_bet_odds() {
        return approved_bet_odds;
    }

    public void setApproved_bet_odds(BigDecimal approved_bet_odds) {
        this.approved_bet_odds = approved_bet_odds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coupon_Bet that = (Coupon_Bet) o;
        return getId() == that.getId() &&
                Objects.equals(getApproved_bet_odds(), that.getApproved_bet_odds());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getApproved_bet_odds());
    }

    @Override
    public String toString() {
        return "Coupon_Bet{" +
                "id=" + id +
                ", approved_bet_odds=" + approved_bet_odds +
                '}';
    }
}
