package group.neo.test.dkruppa.timelog.event;

import group.neo.test.dkruppa.timelog.config.DataSourceStatus;
import group.neo.test.dkruppa.timelog.service.TimeLogQueueService;
import group.neo.test.dkruppa.timelog.service.TimeLogService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Consumer of new data in time log queue
 */
@Slf4j
@Component
@AllArgsConstructor
public class TimeLogConsumer {
    private final TimeLogQueueService queueService;
    private final TimeLogService timeLogService;
    private final DataSourceStatus dataSourceStatus;

    @Scheduled(fixedDelay = 500)
    public void run() {
        while (!queueService.isEmpty() && dataSourceStatus.isDataSourceAvailable()) {
            Date dateToLog = queueService.getFromQueue();
            log.info("Consumed date to log: {}", dateToLog);
            timeLogService.saveTimeLog(dateToLog);
        }
    }
}
