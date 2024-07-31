package group.neo.test.dkruppa.timelog;

import group.neo.test.dkruppa.timelog.event.TimeLogProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Timer;

@Component
@RequiredArgsConstructor
public class ApplicationRunnerTaskExecutor implements ApplicationRunner {
    private final TimeLogProducer timeLogProducer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Timer timer = new Timer();
        timer.schedule(timeLogProducer, 0, 1000);
    }
}
