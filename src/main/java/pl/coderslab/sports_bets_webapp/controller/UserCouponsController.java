package pl.coderslab.sports_bets_webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.sports_bets_webapp.entity.Coupon;
import pl.coderslab.sports_bets_webapp.model.CurrentUser;
import pl.coderslab.sports_bets_webapp.service.CouponService;

import java.util.List;

@Controller
@RequestMapping("/user/coupons")
public class UserCouponsController {

    @Autowired
    CouponService couponService;

    @GetMapping
    public String userCoupons(Model model, @AuthenticationPrincipal CurrentUser currentUser){

        List<Coupon> userCoupons = couponService.findUserCoupons(currentUser.getUser());

        model.addAttribute("userCoupons", userCoupons);

        return "userCoupons";

    }

    @PostMapping
    public String userCouponDetails(@RequestParam int couponID, Model model){

        Coupon coupon = couponService.findByID(couponID);
        coupon.getCoupon_bet();

        model.addAttribute("coupon", coupon);

        return "couponDetails";
    }
}
