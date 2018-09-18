package pl.coderslab.sports_bets_webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SportsBetsWebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsBetsWebappApplication.class, args);
    }
}
