package group.neo.test.dkruppa.timelog.event;

import group.neo.test.dkruppa.timelog.config.DataSourceStatus;
import group.neo.test.dkruppa.timelog.service.TimeLogQueueService;
import group.neo.test.dkruppa.timelog.service.TimeLogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TimeLogConsumerTest {
    @Mock
    private TimeLogQueueService queueService;
    @Mock
    private TimeLogService timeLogService;
    @Mock
    private DataSourceStatus dataSourceStatus;

    @InjectMocks
    private TimeLogConsumer timeLogConsumer;

    @Test
    void shouldSuccessfulSaveTimeLog() {
        // Given
        when(queueService.isEmpty())
                .thenReturn(false)
                .thenReturn(true);
        when(dataSourceStatus.isDataSourceAvailable()).thenReturn(true);
        Date date = new Date();
        when(queueService.getFromQueue()).thenReturn(date);
        // When
        timeLogConsumer.run();
        // Then
        verify(timeLogService).saveTimeLog(date);
    }

    @Test
    void shouldSkipSaveIfDataSourceIsNotAvailable() {
        // Given
        when(queueService.isEmpty())
                .thenReturn(false);
        when(dataSourceStatus.isDataSourceAvailable()).thenReturn(false);
        // When
        timeLogConsumer.run();
        // Then
        verifyNoInteractions(timeLogService);
    }
}
