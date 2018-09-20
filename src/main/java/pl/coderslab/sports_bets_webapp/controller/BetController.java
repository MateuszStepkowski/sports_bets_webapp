package pl.coderslab.sports_bets_webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.sports_bets_webapp.service.CouponService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/bet")
public class BetController {


    @Autowired
    CouponService couponService;


//    @PostMapping("/place/{id}")
//    public String placeBet(@PathVariable Integer id, HttpSession session, HttpServletRequest request) {
//        couponService.addBetToSessionCoupon(id, session, request);
//
//        return "redirect:/home";
//    }
//
//    @DeleteMapping("/remove/{id}")
//    @ResponseBody
//    public double removeBet(HttpSession session, @PathVariable String uuid) {
//        return ticketService.removeBetFromTicket(uuid, session);
//    }
//
//    @GetMapping("")
//    public String showBets(HttpSession session) {
//        Ticket userTicket = session.getAttribute("ticket") == null
//                ? new Ticket()
//                : (Ticket) session.getAttribute("ticket");
//
//        session.setAttribute("bets", userTicket.getBets());
//
//        return "bet/list";
//    }
}