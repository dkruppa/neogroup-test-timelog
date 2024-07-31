package group.neo.test.dkruppa.timelog.event;

import group.neo.test.dkruppa.timelog.service.TimeLogQueueService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class TimeLogProducerTest {
    @Mock
    private TimeLogQueueService queueService;

    @InjectMocks
    private TimeLogProducer timeLogProducer;

    @Test
    void shouldSuccessfulProduceTimeLog() {
        // Given
        // When
        timeLogProducer.run();
        // Then
        verify(queueService).addToQueue(any(Date.class));
        verifyNoMoreInteractions(queueService);
    }
}
