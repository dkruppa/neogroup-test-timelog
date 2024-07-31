package group.neo.test.dkruppa.timelog.event;

import group.neo.test.dkruppa.timelog.config.DataSourceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.stereotype.Component;

/**
 * Listener of data source connection attempts
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataBaseConnectionRetryListener implements RetryListener {
    private final DataSourceStatus dataSourceStatus;

    @Override
    public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        log.warn("Could not connect to database, retrying");
        dataSourceStatus.disableConnection();
    }

    @Override
    public <T, E extends Throwable> void onSuccess(RetryContext context, RetryCallback<T, E> callback, T result) {
        dataSourceStatus.enableConnection();
    }
}
