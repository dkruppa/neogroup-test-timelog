package group.neo.test.dkruppa.timelog.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Service which provide functionality to operate with time log queue
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TimeLogQueueServiceImpl implements TimeLogQueueService {
    private final Queue<Date> queue = new ConcurrentLinkedQueue<>();

    @Override
    public void addToQueue(Date date) {
        queue.add(date);
        log.info("Added to Queue: {}", date);
    }

    @Override
    public Date getFromQueue() {
        Date date = queue.poll();
        log.info("Polled from Queue: {}", date);
        return date;
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
