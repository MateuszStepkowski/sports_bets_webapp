package pl.coderslab.sports_bets_webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_bets_webapp.entity.Bet;
import pl.coderslab.sports_bets_webapp.entity.Coupon;
import pl.coderslab.sports_bets_webapp.entity.Event;
import pl.coderslab.sports_bets_webapp.entity.enums.BetContentEnum;
import pl.coderslab.sports_bets_webapp.entity.enums.BetStatusEnum;
import pl.coderslab.sports_bets_webapp.entity.enums.BetTypeEnum;
import pl.coderslab.sports_bets_webapp.repository.BetRepository;
import pl.coderslab.sports_bets_webapp.service.BetService;

import java.util.List;

@Service
public class BetServiceImpl implements BetService {

    @Autowired
    BetRepository betRepository;

    @Override
    public Bet findFirstInPlayByEvent(Event event) {
        return betRepository.findFirstByEventAndBetType(event, BetTypeEnum.IN_PLAY);
    }

    @Override
    public List<Bet> findAllBeforeGame(Event event) {
        return betRepository.findAllByEventAndBetType(event, BetTypeEnum.BEFORE_GAME);
    }

    @Override
    public List<Bet> findAllInPlay(Event event) {
        return betRepository.findAllByEventAndBetType(event, BetTypeEnum.IN_PLAY);
    }

    @Override
    public void saveAll(List<Bet> bets) {
        betRepository.saveAll(bets);
    }

    @Override
    public List<Bet> findAllWaitingWithEndedEvent() {
        return betRepository.findAllByBetStatusAndEventEndDate(BetStatusEnum.WAITING, null);
    }


    @Override
    public List<Bet> findAllByCoupon(Coupon coupon) {
        return betRepository.findAllBycoupon(coupon.getId());
    }

    @Override
    public Bet save(Bet bet) {
        return betRepository.saveAndFlush(bet);
    }


    @Override
    public Bet[] getEventBetsInArrayByType(Event event, BetTypeEnum betType) {
        Bet[] resultBets = new Bet[3];
        for (Bet bet: event.getBets()){
            if (bet.getBetType().equals(betType)){
                if ( bet.getBetContent().equals(BetContentEnum.TeamA)){
                    resultBets[0] = bet;
                }else if (bet.getBetContent().equals(BetContentEnum.DRAW)){
                    resultBets[1] = bet;
                }else {
                    resultBets[2] = bet;
                }
            }
        }
        return resultBets;
    }


    @Override
    public void checkAndSetWinOrLost(Bet bet) {

        if (bet.getBetContent().equals(BetContentEnum.TeamA)){

            if (bet.getEvent().getTeamA_pts() > bet.getEvent().getTeamB_pts()){

                bet.setBetStatus(BetStatusEnum.WON);
            }else {

                bet.setBetStatus(BetStatusEnum.LOST);
            }

        }else if (bet.getBetContent().equals(BetContentEnum.TeamB)){

            if (bet.getEvent().getTeamA_pts() < bet.getEvent().getTeamB_pts()){

                bet.setBetStatus(BetStatusEnum.WON);
            }else {
                bet.setBetStatus(BetStatusEnum.LOST);
            }

        }else {

            if (bet.getEvent().getTeamA_pts() == bet.getEvent().getTeamB_pts()){

                bet.setBetStatus(BetStatusEnum.WON);
            }else {
                bet.setBetStatus(BetStatusEnum.LOST);
            }
        }
    }

    public Bet findByID(int ID){
        Bet bet = betRepository.findFirstById(ID);
        return bet;
    }


}
