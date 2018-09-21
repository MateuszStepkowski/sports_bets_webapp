package pl.coderslab.sports_bets_webapp.service;

import pl.coderslab.sports_bets_webapp.model.CurrentCoupon;

import java.math.BigDecimal;

public interface CurrentCouponToCouponService {

    String convertAndSave(CurrentCoupon currentCoupon, BigDecimal stake);
}
