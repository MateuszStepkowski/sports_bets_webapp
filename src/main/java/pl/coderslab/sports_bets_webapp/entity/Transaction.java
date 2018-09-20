package pl.coderslab.sports_bets_webapp.entity;

import pl.coderslab.sports_bets_webapp.entity.enums.TransactionTypeEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

@Entity(name = "transactions")
public class Transaction {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne
    private Wallet wallet;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionTypeEnum transactionType;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Timestamp time;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public TransactionTypeEnum getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionTypeEnum transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return getId() == that.getId() &&
                Objects.equals(getWallet(), that.getWallet()) &&
                getTransactionType() == that.getTransactionType() &&
                Objects.equals(getAmount(), that.getAmount()) &&
                Objects.equals(getTime(), that.getTime());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getWallet(), getTransactionType(), getAmount(), getTime());
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionType=" + transactionType +
                ", amount=" + amount +
                ", time=" + time +
                '}';
    }
}
