package pl.coderslab.sports_bets_webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_bets_webapp.entity.Bet;
import pl.coderslab.sports_bets_webapp.entity.Coupon;
import pl.coderslab.sports_bets_webapp.entity.Coupon_Bet;
import pl.coderslab.sports_bets_webapp.entity.enums.BetStatusEnum;
import pl.coderslab.sports_bets_webapp.entity.enums.CouponStatusEnum;
import pl.coderslab.sports_bets_webapp.service.BetService;
import pl.coderslab.sports_bets_webapp.service.CouponService;
import pl.coderslab.sports_bets_webapp.service.CouponSettleService;
import pl.coderslab.sports_bets_webapp.service.TransactionService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CouponSettleServiceImpl implements CouponSettleService {


    @Autowired
    BetService betService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    CouponService couponService;

    @Override
    public void updateStatusAndSettle(Coupon coupon) {

        List<Bet>  couponsBets = betService.findAllByCoupon(coupon);
        int win_counter = 0;
        for (Bet bet : couponsBets){
            if (bet.getBetStatus().equals(BetStatusEnum.LOST)){
                coupon.setCouponStatus(CouponStatusEnum.LOST);
                break;
            }
            if (bet.getBetStatus().equals(BetStatusEnum.WON)){
                win_counter ++;
            }
        }

        if (coupon.getCouponStatus().equals(CouponStatusEnum.WAITING)){
            return;
        }else if (couponsBets.size() == win_counter){
            coupon.setCouponStatus(CouponStatusEnum.PAID);
            payThePrize(coupon);
            //TODO send message to user
        }else {
            //TODO send message to user
        }
        couponService.save(coupon);
    }


    private void payThePrize(Coupon coupon) {

        BigDecimal finalRate = BigDecimal.valueOf(1);
        for (Coupon_Bet coupon_bet : coupon.getCoupon_bet()){

            finalRate = coupon_bet.getApproved_bet_odds().multiply(finalRate);
        }

        transactionService.couponPayOut(coupon.getUser(), coupon.getAmount().multiply(finalRate));

    }
}
