package group.neo.test.dkruppa.timelog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableRetry
@Slf4j
public class TimeLogApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TimeLogApplication.class, args);
    }
}
