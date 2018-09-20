package pl.coderslab.sports_bets_webapp.entity;

import pl.coderslab.sports_bets_webapp.entity.enums.CouponStatusEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "coupons")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    private BigDecimal amount;

    @OneToMany(mappedBy = "coupon")
    private List<Coupon_Bet> coupon_bet = new ArrayList<>();


    @NotNull
    @Enumerated(EnumType.STRING)
    private CouponStatusEnum couponStatus;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<Coupon_Bet> getCoupon_bet() {
        return coupon_bet;
    }

    public void setCoupon_bet(List<Coupon_Bet> coupon_bet) {
        this.coupon_bet = coupon_bet;
    }

    public CouponStatusEnum getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(CouponStatusEnum couponStatus) {
        this.couponStatus = couponStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coupon coupon = (Coupon) o;
        return getId() == coupon.getId();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }


}
