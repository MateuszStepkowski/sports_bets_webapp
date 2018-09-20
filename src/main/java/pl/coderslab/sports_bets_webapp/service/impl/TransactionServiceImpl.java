package pl.coderslab.sports_bets_webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_bets_webapp.entity.Transaction;
import pl.coderslab.sports_bets_webapp.entity.User;
import pl.coderslab.sports_bets_webapp.entity.Wallet;
import pl.coderslab.sports_bets_webapp.entity.enums.TransactionTypeEnum;
import pl.coderslab.sports_bets_webapp.repository.TransactionRepository;
import pl.coderslab.sports_bets_webapp.service.DecimalToStringService;
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

    @Autowired
    DecimalToStringService decimalToStringService;


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
    public String userDeposit(User user, String deposit) {

        BigDecimal amount = decimalToStringService.parse(deposit);

        if (amount == null){
            return "Invalid format, try again";

        }
        if (amount.compareTo(BigDecimal.valueOf(0))< 0) {
            return "Invalid amount, try again";
        }

        Transaction transaction = new Transaction();
        Wallet wallet = user.getWallet();

        transaction.setAmount(amount);
        transaction.setWallet(wallet);
        transaction.setTransactionType(TransactionTypeEnum.DEPOSIT);
        transaction.setTime(new Timestamp(System.currentTimeMillis()));

        wallet.setBalance(wallet.getBalance().add(amount));
        save(transaction);
        walletService.save(wallet);

        return "success";

    }

    @Override
    public String userWithDraw(User user, String withdraw) {

        BigDecimal amount = decimalToStringService.parse(withdraw);

        if (amount == null){
            return "Invalid format, try again";

        }
        if (user.getWallet().getBalance().compareTo(amount) < 0) {
            return "Not Enough Founds";
        }

        Transaction transaction = new Transaction();
        Wallet wallet = user.getWallet();

        transaction.setAmount(amount);
        transaction.setWallet(wallet);
        transaction.setTransactionType(TransactionTypeEnum.WITHDRAW);
        transaction.setTime(new Timestamp(System.currentTimeMillis()));

        wallet.setBalance(wallet.getBalance().subtract(amount));
        save(transaction);
        walletService.save(wallet);

        return "success";
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
