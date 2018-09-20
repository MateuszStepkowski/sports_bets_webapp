package pl.coderslab.sports_bets_webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_bets_webapp.entity.Role;

public interface RoleRepository extends JpaRepository <Role, Integer> {

    Role findFirstByName(String name);
}
