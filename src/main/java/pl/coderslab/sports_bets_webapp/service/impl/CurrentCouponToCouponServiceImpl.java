package pl.coderslab.sports_bets_webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_bets_webapp.entity.Bet;
import pl.coderslab.sports_bets_webapp.entity.Coupon;
import pl.coderslab.sports_bets_webapp.entity.Coupon_Bet;
import pl.coderslab.sports_bets_webapp.entity.enums.CouponStatusEnum;
import pl.coderslab.sports_bets_webapp.model.CurrentCoupon;
import pl.coderslab.sports_bets_webapp.repository.BetRepository;
import pl.coderslab.sports_bets_webapp.repository.Coupon_BetRepository;
import pl.coderslab.sports_bets_webapp.service.BetService;
import pl.coderslab.sports_bets_webapp.service.CouponService;
import pl.coderslab.sports_bets_webapp.service.CurrentCouponToCouponService;
import pl.coderslab.sports_bets_webapp.service.TransactionService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrentCouponToCouponServiceImpl implements CurrentCouponToCouponService {

    @Autowired
    BetRepository betRepository;

    @Autowired
    CouponService couponService;

    @Autowired
    Coupon_BetRepository coupon_betRepository;

    @Autowired
    TransactionService transactionService;


    @Override
    public String convertAndSave(CurrentCoupon currentCoupon, BigDecimal stake) {

        if (currentCoupon.getUser().getWallet().getBalance().compareTo(stake)<0){
            return "Not enough Founds, please recharge your wallet";
        }
        List<Coupon_Bet> coupon_betList = new ArrayList<>();
        for (Bet bet : currentCoupon.getBets()){
            Bet betFromDB = betRepository.findFirstById(bet.getId());
            if (bet.getActual_odds().compareTo(betFromDB.getActual_odds()) !=0 ){
                return "odds already changed, coupon invalidated, please create new Coupon";
            }else {
                coupon_betList.add(new Coupon_Bet(betFromDB, betFromDB.getActual_odds()));
            }
        }



        Coupon coupon = new Coupon(currentCoupon.getUser());
        coupon.setCouponStatus(CouponStatusEnum.WAITING);
        coupon.setAmount(stake);
        coupon = couponService.save(coupon);
        for (Coupon_Bet coupon_bet : coupon_betList){
            coupon_bet.setCoupon(coupon);
            coupon_betRepository.save(coupon_bet);
        }
        transactionService.couponCharge(coupon.getUser(),stake);
        return "success";
    }
}
