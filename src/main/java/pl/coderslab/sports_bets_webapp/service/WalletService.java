package pl.coderslab.sports_bets_webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.sports_bets_webapp.entity.Wallet;
import pl.coderslab.sports_bets_webapp.repository.WalletRepository;

public interface WalletService {

    Wallet save(Wallet wallet);
}
