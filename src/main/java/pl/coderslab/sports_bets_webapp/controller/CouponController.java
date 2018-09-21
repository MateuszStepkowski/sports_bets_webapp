package pl.coderslab.sports_bets_webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import pl.coderslab.sports_bets_webapp.entity.Bet;
import pl.coderslab.sports_bets_webapp.entity.Coupon;
import pl.coderslab.sports_bets_webapp.entity.Wallet;
import pl.coderslab.sports_bets_webapp.entity.enums.CouponStatusEnum;
import pl.coderslab.sports_bets_webapp.model.CurrentCoupon;
import pl.coderslab.sports_bets_webapp.model.CurrentUser;
import pl.coderslab.sports_bets_webapp.service.CouponService;
import pl.coderslab.sports_bets_webapp.service.CurrentCouponToCouponService;
import pl.coderslab.sports_bets_webapp.service.TransactionService;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    CurrentCouponToCouponService currentCouponToCouponService;

    @PostMapping("/place")
    public String placeCoupon(@RequestParam BigDecimal stake, Model model,
                              HttpSession session, @AuthenticationPrincipal CurrentUser currentUser) {


        CurrentCoupon currentCoupon = (CurrentCoupon) session.getAttribute("currentCoupon");

        String message = currentCouponToCouponService.convertAndSave(currentCoupon, stake);
        model.addAttribute("message", message);
        session.setAttribute("currentCoupon", new CurrentCoupon(currentUser.getUser()));
        return "redirect:/";
    }

    @PostMapping("/addBet")
    public String addBet(@RequestParam Bet bet, HttpSession session, @AuthenticationPrincipal CurrentUser currentUser){
        CurrentCoupon currentCoupon = (CurrentCoupon) session.getAttribute("currentCoupon");
        if (currentCoupon == null) {
            currentCoupon = new CurrentCoupon(currentUser.getUser());
        }

        currentCoupon.getBets().add(bet);
        currentCoupon.setTotalOdds();
        session.setAttribute("currentCoupon", currentCoupon);

        return ":redirect:/";
    }

    @PostMapping("/deleteBet")
    public String deleteBet(@RequestParam Bet bet, HttpSession session){
        CurrentCoupon currentCoupon = (CurrentCoupon) session.getAttribute("currentCoupon");

        currentCoupon.getBets().remove(bet);
        currentCoupon.setTotalOdds();
        session.setAttribute("currentCoupon", currentCoupon);

        return ":redirect:/";
    }

}