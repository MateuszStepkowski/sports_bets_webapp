package pl.coderslab.sports_bets_webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import pl.coderslab.sports_bets_webapp.service.EventsExternalDataService;
import pl.coderslab.sports_bets_webapp.service.LiveEventsService;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.Executor;


@SpringBootApplication
@EnableScheduling
public class SportsBetsWebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportsBetsWebappApplication.class, args);
    }

    @Autowired
    EventsExternalDataService externalDataService;

    @Autowired
    LiveEventsService liveEventsService;


    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Warsaw"));   // It will set UTC timezone
        System.out.println("Spring boot application running in UTC timezone :"+new Date());   // It will print UTC timezone
    }



}
