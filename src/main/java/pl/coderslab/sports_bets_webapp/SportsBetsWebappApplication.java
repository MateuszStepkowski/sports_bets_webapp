package pl.coderslab.sports_bets_webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;


@SpringBootApplication
@EnableScheduling
public class SportsBetsWebappApplication implements SchedulingConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(SportsBetsWebappApplication.class, args);
    }


    @PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Warsaw"));   // It will set UTC timezone
        System.out.println("Spring boot application running in UTC timezone :"+new Date());   // It will print UTC timezone
    }


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        ThreadPoolTaskScheduler thPoolTaskScheduler = new ThreadPoolTaskScheduler();
        thPoolTaskScheduler.setPoolSize(3);
        thPoolTaskScheduler.setThreadNamePrefix("my-scheduled-task-pool-");
        thPoolTaskScheduler.initialize();
        scheduledTaskRegistrar.setTaskScheduler(thPoolTaskScheduler);
    }
}
