package pl.coderslab.sports_bets_webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_bets_webapp.entity.Transaction;
import pl.coderslab.sports_bets_webapp.entity.User;
import pl.coderslab.sports_bets_webapp.entity.Wallet;
import pl.coderslab.sports_bets_webapp.entity.enums.TransactionTypeEnum;
import pl.coderslab.sports_bets_webapp.repository.TransactionRepository;
import pl.coderslab.sports_bets_webapp.service.TransactionService;
import pl.coderslab.sports_bets_webapp.service.WalletService;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    WalletService walletService;

    @Autowired
    TransactionRepository transactionRepository;


    @Override
    public void couponPayOut(User user, BigDecimal payout) {

        Transaction transaction = new Transaction();
        Wallet wallet = user.getWallet();

        transaction.setAmount(payout);
        transaction.setWallet(wallet);
        transaction.setTransactionType(TransactionTypeEnum.COUPON_PAYOUT);
        transaction.setTime(new Timestamp(System.currentTimeMillis()));

        wallet.setBalance(wallet.getBalance().add(payout));
        save(transaction);
        walletService.save(wallet);

    }

    @Override
    public void userDeposit(User user, BigDecimal deposit) {

        Transaction transaction = new Transaction();
        Wallet wallet = user.getWallet();

        transaction.setAmount(deposit);
        transaction.setWallet(wallet);
        transaction.setTransactionType(TransactionTypeEnum.DEPOSIT);
        transaction.setTime(new Timestamp(System.currentTimeMillis()));

        wallet.setBalance(wallet.getBalance().add(deposit));
        save(transaction);
        walletService.save(wallet);

    }

    @Override
    public void userWithDraw(User user, BigDecimal withdraw) {

        Transaction transaction = new Transaction();
        Wallet wallet = user.getWallet();

        transaction.setAmount(withdraw);
        transaction.setWallet(wallet);
        transaction.setTransactionType(TransactionTypeEnum.WITHDRAW);
        transaction.setTime(new Timestamp(System.currentTimeMillis()));

        wallet.setBalance(wallet.getBalance().subtract(withdraw));
        save(transaction);
        walletService.save(wallet);

    }

    @Override
    public void couponCharge(User user, BigDecimal charge) {

        Transaction transaction = new Transaction();
        Wallet wallet = user.getWallet();

        transaction.setAmount(charge);
        transaction.setWallet(wallet);
        transaction.setTransactionType(TransactionTypeEnum.COUPON_CHARGE);
        transaction.setTime(new Timestamp(System.currentTimeMillis()));

        wallet.setBalance(wallet.getBalance().subtract(charge));
        save(transaction);
        walletService.save(wallet);

    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.saveAndFlush(transaction);
    }
}
