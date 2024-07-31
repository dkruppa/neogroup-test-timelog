package group.neo.test.dkruppa.timelog.event;

import group.neo.test.dkruppa.timelog.config.DataSourceStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class DataBaseConnectionRetryListenerTest {
    @Mock
    private DataSourceStatus dataSourceStatus;

    @InjectMocks
    private DataBaseConnectionRetryListener connectionRetryListener;

    @Test
    void shouldSuccessfulProcessOnErrorEvent() {
        // Given
        // When
        connectionRetryListener.onError(mock(RetryContext.class), mock(RetryCallback.class), mock(Throwable.class));
        // Then
        verify(dataSourceStatus).disableConnection();
        verifyNoMoreInteractions(dataSourceStatus);
    }

    @Test
    void shouldSuccessfulProcessOnSuccessEvent() {
        // Given
        // When
        connectionRetryListener.onSuccess(mock(RetryContext.class), mock(RetryCallback.class), mock(Throwable.class));
        // Then
        verify(dataSourceStatus).enableConnection();
        verifyNoMoreInteractions(dataSourceStatus);
    }
}
