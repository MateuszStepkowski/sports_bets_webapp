package pl.coderslab.sports_bets_webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.sports_bets_webapp.entity.Coupon;
import pl.coderslab.sports_bets_webapp.entity.Wallet;
import pl.coderslab.sports_bets_webapp.entity.enums.CouponStatusEnum;
import pl.coderslab.sports_bets_webapp.service.CouponService;
import pl.coderslab.sports_bets_webapp.service.TransactionService;

import java.math.BigDecimal;

@Controller
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;

    @Autowired
    TransactionService transactionService;

    @PostMapping("/place")
    public String placeCoupon(@RequestParam Coupon coupon, @RequestParam BigDecimal stake, Model model) {

        Wallet wallet;// = currentUser.getWallet();


//        if (wallet.getBalance().compareTo(stake) < 0) {
//
//            model.addAttribute("walletError", "Not Enough Founds");
//            return "user/wallet";
//        }

        coupon.setCouponStatus(CouponStatusEnum.WAITING);
        couponService.save(coupon);
//        transactionService.save(currentUser, coupon.getAmount());

        return "redirect:/";
    }

}