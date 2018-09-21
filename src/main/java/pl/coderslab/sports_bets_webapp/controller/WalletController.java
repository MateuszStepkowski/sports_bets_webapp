package pl.coderslab.sports_bets_webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import pl.coderslab.sports_bets_webapp.entity.Transaction;
import pl.coderslab.sports_bets_webapp.entity.Wallet;
import pl.coderslab.sports_bets_webapp.model.CurrentUser;
import pl.coderslab.sports_bets_webapp.service.DecimalToStringService;
import pl.coderslab.sports_bets_webapp.service.TransactionService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

@Controller
@RequestMapping("/user/wallet")
public class WalletController {

    @Autowired
    TransactionService transactionService;


    @GetMapping("/")
    public String displayWallet(Model model, @AuthenticationPrincipal CurrentUser currentUser){

        Wallet wallet = currentUser.getUser().getWallet();
        model.addAttribute("balance", wallet.getBalance().setScale(2, RoundingMode.HALF_DOWN));

        return "userWallet";
    }


    @PostMapping("/withdraw")
    public String withdraw(@RequestParam String withdrawAmount, Model model, @AuthenticationPrincipal CurrentUser currentUser) {

        String message = transactionService.userWithDraw(currentUser.getUser(), withdrawAmount);

        Wallet wallet = currentUser.getUser().getWallet();
        model.addAttribute("balance", wallet.getBalance().setScale(2, RoundingMode.HALF_DOWN));
        model.addAttribute("message", message);

        return "userWallet";
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam String depositAmount, Model model, @AuthenticationPrincipal CurrentUser currentUser) {

        String message = transactionService.userDeposit(currentUser.getUser(), depositAmount);

        Wallet wallet = currentUser.getUser().getWallet();
        model.addAttribute("balance", wallet.getBalance().setScale(2, RoundingMode.HALF_DOWN));
        model.addAttribute("message", message);

        return "userWallet";
        }

    @PostMapping("/allTransactions")
    public String transactionsHistory(@AuthenticationPrincipal CurrentUser currentUser, Model model){

        List<Transaction> userTransactions = transactionService.findAllByWallet(currentUser.getUser().getWallet());
        model.addAttribute("transactionsHistory", userTransactions);

        return "transactionsHistory";
    }
}