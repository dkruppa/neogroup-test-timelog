package group.neo.test.dkruppa.timelog.service;

import group.neo.test.dkruppa.timelog.model.TimeLog;
import group.neo.test.dkruppa.timelog.repository.TimeLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Service which provide read/write operations with TimeLog entity
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TimeLogServiceImpl implements TimeLogService {
    private final TimeLogRepository timeLogRepository;

    @Override
    public List<TimeLog> findAll() {
        log.info("Get all time logs");
        return timeLogRepository.findAll();
    }

    @Override
    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 5000), listeners = "dataBaseConnectionRetryListener")
    public TimeLog saveTimeLog(Date date) {
        log.info("Save time log: {}", date.toString());
        TimeLog timeLog = TimeLog.builder().dateTime(date).build();
        return timeLogRepository.save(timeLog);
    }
}
