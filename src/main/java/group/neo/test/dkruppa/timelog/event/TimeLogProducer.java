package group.neo.test.dkruppa.timelog.event;

import group.neo.test.dkruppa.timelog.service.TimeLogQueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.TimerTask;

/**
 * Producer of new data into time log queue
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TimeLogProducer extends TimerTask {
    private final TimeLogQueueService timeLogQueueService;

    @Override
    public void run() {
        produceTimeLog();
    }

    private void produceTimeLog() {
        Date dateToLog = new Date();
        log.info("Produced date to log: {}", dateToLog);
        timeLogQueueService.addToQueue(dateToLog);
    }
}
