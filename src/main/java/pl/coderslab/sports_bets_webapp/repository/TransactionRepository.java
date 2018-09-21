package pl.coderslab.sports_bets_webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.sports_bets_webapp.entity.Transaction;
import pl.coderslab.sports_bets_webapp.entity.Wallet;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findAllByWallet(Wallet wallet);
}
