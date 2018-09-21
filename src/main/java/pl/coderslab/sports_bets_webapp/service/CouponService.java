package pl.coderslab.sports_bets_webapp.service;

import pl.coderslab.sports_bets_webapp.entity.Coupon;
import pl.coderslab.sports_bets_webapp.entity.User;
import pl.coderslab.sports_bets_webapp.entity.enums.CouponStatusEnum;

import java.util.List;

public interface CouponService {


    List<Coupon> findAllWaiting();


    Coupon save(Coupon coupon);

    List<Coupon> findUserCoupons(User user);

    Coupon findByID(int id);

}
