package pl.coderslab.sports_bets_webapp.service;

import pl.coderslab.sports_bets_webapp.entity.Coupon;
import pl.coderslab.sports_bets_webapp.entity.enums.CouponStatusEnum;

public interface CouponSettleService {


    void updateStatusAndSettle(Coupon coupon);


}
