package pl.coderslab.sports_bets_webapp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_bets_webapp.entity.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findAllByStartDateBeforeAndEndDateIsNull(LocalDateTime startDate);


}
