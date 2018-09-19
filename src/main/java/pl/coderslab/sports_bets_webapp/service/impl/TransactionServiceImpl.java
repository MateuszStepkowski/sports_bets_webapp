package pl.coderslab.sports_bets_webapp.service.impl;

import org.springframework.stereotype.Service;
import pl.coderslab.sports_bets_webapp.entity.User;
import pl.coderslab.sports_bets_webapp.service.TransactionService;

import java.math.BigDecimal;

@Service
public class TransactionServiceImpl implements TransactionService {


    @Override
    public void couponPayOut(User user, BigDecimal payout) {
        
    }

    @Override
    public void userDeposit(User user, BigDecimal deposit) {

    }

    @Override
    public void userWithDraw(User user, BigDecimal withdraw) {

    }

    @Override
    public void couponCharge(User user, BigDecimal charge) {

    }
}
