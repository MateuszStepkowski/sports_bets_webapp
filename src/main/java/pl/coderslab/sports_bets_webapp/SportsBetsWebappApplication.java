package pl.coderslab.sports_bets_webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.coderslab.sports_bets_webapp.service.EventsExternalDataService;
import pl.coderslab.sports_bets_webapp.service.InPlayEventsService;

@EnableScheduling
@SpringBootApplication
public class SportsBetsWebappApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SportsBetsWebappApplication.class, args);
    }

    @Autowired
    EventsExternalDataService externalDataService;

    @Autowired
    InPlayEventsService inPlayService;


    @Override
    public void run(String... args) throws Exception {


    }
}
