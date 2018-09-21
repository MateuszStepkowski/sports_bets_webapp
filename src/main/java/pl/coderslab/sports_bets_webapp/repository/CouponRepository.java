package pl.coderslab.sports_bets_webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.sports_bets_webapp.entity.Coupon;
import pl.coderslab.sports_bets_webapp.entity.User;
import pl.coderslab.sports_bets_webapp.entity.enums.CouponStatusEnum;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    List<Coupon> findAllByCouponStatus(CouponStatusEnum couponStatus);

    List<Coupon> findAllByUserOrderByCouponStatus(User user);

    Coupon findFirstById(int id);
}
