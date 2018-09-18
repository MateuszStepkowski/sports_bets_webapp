package pl.coderslab.sports_bets_webapp.entity.enums;

import javax.persistence.Entity;

public enum TransactionTypeEnum {

    DEPOSIT,
    WITHDRAW,
    COUPON_PAYOUT,
    COUPON_CHARGE;
}
