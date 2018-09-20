package pl.coderslab.sports_bets_webapp.service;

import pl.coderslab.sports_bets_webapp.entity.Transaction;
import pl.coderslab.sports_bets_webapp.entity.User;

import java.math.BigDecimal;

public interface TransactionService {

    void couponPayOut(User user, BigDecimal payout);

    String userDeposit(User user, String deposit);

    String userWithDraw(User user, String withdraw);

    void couponCharge(User user, BigDecimal charge);

    Transaction save(Transaction transaction);

}
