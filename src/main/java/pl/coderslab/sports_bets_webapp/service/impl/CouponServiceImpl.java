package pl.coderslab.sports_bets_webapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_bets_webapp.entity.Coupon;
import pl.coderslab.sports_bets_webapp.entity.enums.CouponStatusEnum;
import pl.coderslab.sports_bets_webapp.repository.CouponRepository;
import pl.coderslab.sports_bets_webapp.service.CouponService;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponRepository couponRepository;

    @Override
    public List<Coupon> findAllWaiting() {
        return couponRepository.findAllByCouponStatus(CouponStatusEnum.WAITING);
    }
}
