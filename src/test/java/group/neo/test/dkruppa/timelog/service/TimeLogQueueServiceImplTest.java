package group.neo.test.dkruppa.timelog.service;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class TimeLogQueueServiceImplTest {

    TimeLogQueueService timeLogQueueService = new TimeLogQueueServiceImpl();

    @Test
    void shouldSuccessfulAddAndPollDateToQueue() {
        assertThat(timeLogQueueService.isEmpty()).isTrue();
        Date date = new Date();
        timeLogQueueService.addToQueue(date);
        assertThat(timeLogQueueService.isEmpty()).isFalse();
        Date fromQueue = timeLogQueueService.getFromQueue();
        assertThat(timeLogQueueService.isEmpty()).isTrue();
        assertThat(fromQueue).isEqualTo(date);
    }
}
