package pl.coderslab.sports_bets_webapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.sports_bets_webapp.entity.Bet;
import pl.coderslab.sports_bets_webapp.entity.Coupon;
import pl.coderslab.sports_bets_webapp.entity.Wallet;
import pl.coderslab.sports_bets_webapp.entity.enums.CouponStatusEnum;
import pl.coderslab.sports_bets_webapp.model.CurrentCoupon;
import pl.coderslab.sports_bets_webapp.model.CurrentUser;
import pl.coderslab.sports_bets_webapp.service.BetService;
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
    BetService betService;

    @Autowired
    CurrentCouponToCouponService currentCouponToCouponService;

    @PostMapping("/place")
    public String placeCoupon(@RequestParam BigDecimal stake, Model model,
                              HttpSession session, @AuthenticationPrincipal CurrentUser currentUser) {


        CurrentCoupon currentCoupon = (CurrentCoupon) session.getAttribute("currentCoupon");

        String message = currentCouponToCouponService.convertAndSave(currentCoupon, stake);
        model.addAttribute("message", message);
        session.setAttribute("currentCoupon", new CurrentCoupon(currentUser.getUser()));
        return "homePage";
    }

    @PostMapping("/addBet")
    public String addBet(@RequestParam int betID, HttpSession session, @AuthenticationPrincipal CurrentUser currentUser){

        Bet choosenBet = betService.findByID(betID);
        CurrentCoupon currentCoupon = (CurrentCoupon) session.getAttribute("currentCoupon");
        if (currentCoupon == null) {
            currentCoupon = new CurrentCoupon(currentUser.getUser());
        }

        currentCoupon.getBets().add(choosenBet);
        currentCoupon.setTotalOdds();
        session.setAttribute("currentCoupon", currentCoupon);

        return "redirect:/home";
    }

    @PostMapping("/deleteBet")
    public String deleteBet(@RequestParam int betID, HttpSession session){
        Bet choosenBet = betService.findByID(betID);
        CurrentCoupon currentCoupon = (CurrentCoupon) session.getAttribute("currentCoupon");

        currentCoupon.getBets().remove(choosenBet);
        currentCoupon.setTotalOdds();
        session.setAttribute("currentCoupon", currentCoupon);

        return "redirect:/home";
    }

}