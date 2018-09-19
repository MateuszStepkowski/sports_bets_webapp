package pl.coderslab.sports_bets_webapp.service;

import pl.coderslab.sports_bets_webapp.entity.Coupon;
import pl.coderslab.sports_bets_webapp.entity.enums.CouponStatusEnum;

import java.util.List;

public interface CouponService {


    List<Coupon> findAllWaiting();



}
