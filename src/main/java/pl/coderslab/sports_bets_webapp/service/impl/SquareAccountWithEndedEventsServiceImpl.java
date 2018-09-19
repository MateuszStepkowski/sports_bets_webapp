package pl.coderslab.sports_bets_webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import pl.coderslab.sports_bets_webapp.entity.Bet;
import pl.coderslab.sports_bets_webapp.entity.Coupon;
import pl.coderslab.sports_bets_webapp.service.*;

import java.util.List;

public class SquareAccountWithEndedEventsServiceImpl implements SquareAccountWithEndedEventsService {

    @Autowired
    EventService eventService;

    @Autowired
    BetService betService;

    @Autowired
    CouponService couponService;

    @Autowired
    CouponSettleService couponSettleService;

    @Override
    @Scheduled
    public void update_Waiting_Bets_Statuses() {

        List<Bet> waitingBets = betService.findAllWaitingWithEndedEvent();
        for (Bet bet : waitingBets){
            betService.checkAndSetWinOrLost(bet);
        }
    }

    @Override
    @Scheduled
    public void settle_coupons() {

        List<Coupon> waitingCoupons = couponService.findAllWaiting();

        for (Coupon coupon : waitingCoupons){
            couponSettleService.updateStatusAndSettle(coupon);
        }
    }
}
