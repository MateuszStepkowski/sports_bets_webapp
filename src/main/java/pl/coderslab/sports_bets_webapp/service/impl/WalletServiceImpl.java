package pl.coderslab.sports_bets_webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_bets_webapp.entity.Wallet;
import pl.coderslab.sports_bets_webapp.repository.WalletRepository;
import pl.coderslab.sports_bets_webapp.service.WalletService;

@Service
public class WalletServiceImpl implements WalletService {

 @Autowired
    WalletRepository walletRepository;

    @Override
    public Wallet save(Wallet wallet) {
        return walletRepository.saveAndFlush(wallet);
    }
}
