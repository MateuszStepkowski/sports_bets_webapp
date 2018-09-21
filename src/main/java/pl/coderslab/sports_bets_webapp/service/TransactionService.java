package pl.coderslab.sports_bets_webapp.service;

import pl.coderslab.sports_bets_webapp.entity.Transaction;
import pl.coderslab.sports_bets_webapp.entity.User;
import pl.coderslab.sports_bets_webapp.entity.Wallet;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    void couponPayOut(User user, BigDecimal payout);

    String userDeposit(User user, String deposit);

    String userWithDraw(User user, String withdraw);

    void couponCharge(User user, BigDecimal charge);

    Transaction save(Transaction transaction);

    List<Transaction> findAllByWallet(Wallet wallet);

}
