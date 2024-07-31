package group.neo.test.dkruppa.timelog.service;

import group.neo.test.dkruppa.timelog.model.TimeLog;
import group.neo.test.dkruppa.timelog.repository.TimeLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TimeLogServiceImplTest {
    @Mock
    private TimeLogRepository timeLogRepository;
    @Captor
    ArgumentCaptor<TimeLog> timeLogCaptor;

    @InjectMocks
    private TimeLogServiceImpl timeLogService;

    @Test
    void shouldReturnAllTimeLogs() {
        // Given
        List<TimeLog> timeLogList = Arrays.asList(mock(TimeLog.class), mock(TimeLog.class), mock(TimeLog.class));
        when(timeLogRepository.findAll()).thenReturn(timeLogList);
        // When
        List<TimeLog> result = timeLogService.findAll();
        // Then
        assertThat(result).isEqualTo(timeLogList);
    }

    @Test
    void shouldSuccessfulSaveTimeLog() {
        // Given
        Date date = new Date();
        // When
        timeLogService.saveTimeLog(date);
        // Then
        verify(timeLogRepository).save(timeLogCaptor.capture());
        TimeLog timeLogCaptorValue = timeLogCaptor.getValue();
        assertThat(timeLogCaptorValue.getDateTime()).isEqualTo(date);
    }

}
