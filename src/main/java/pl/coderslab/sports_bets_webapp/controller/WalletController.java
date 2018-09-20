package pl.coderslab.sports_bets_webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.sports_bets_webapp.entity.Wallet;
import pl.coderslab.sports_bets_webapp.service.TransactionService;

import java.math.BigDecimal;

@Controller
@RequestMapping("/user/wallet")
public class WalletController {

    @Autowired
    TransactionService transactionService;


//    @PostMapping("/withdraw")
//    public String withdraw(@RequestParam BigDecimal amount, Model model) {
//        Wallet wallet; //TO DO currentUser Get Wallet
//
//        if (wallet.getBalance().compareTo(amount) < 0) {
//            model.addAttribute("walletError", "Not Enough Founds");
//            return "/user/wallet";
//        }
//
//        transactionService.userWithDraw(currentUser, amount);
//
//        return "redirect:/user/balance";
//    }
//
//    @PostMapping("/deposit")
//    public String deposit(@RequestParam BigDecimal amount, Model model) {
//
//        if (amount.compareTo(BigDecimal.valueOf(0)) <= 0) {
//            transactionService.userDeposit(currentUser, amount);
//
//            return "redirect:/user/balance";
//        }
//
//        model.addAttribute("walletError", "Not Enough Founds");
//        return "user/wallet";
//    }
}