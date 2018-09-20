package pl.coderslab.sports_bets_webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_bets_webapp.entity.Role;
import pl.coderslab.sports_bets_webapp.entity.User;
import pl.coderslab.sports_bets_webapp.entity.Wallet;
import pl.coderslab.sports_bets_webapp.repository.RoleRepository;
import pl.coderslab.sports_bets_webapp.repository.UserRepository;
import pl.coderslab.sports_bets_webapp.repository.WalletRepository;
import pl.coderslab.sports_bets_webapp.service.UserService;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    WalletRepository walletRepository;

    @Override
    public User findByLogin(String login) {
        return userRepository.findFirstByLogin(login);
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findFirstByName("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.saveAndFlush(user);
        Wallet wallet = new Wallet(user);
        walletRepository.save(wallet);
    }
}
