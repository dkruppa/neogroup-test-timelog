package group.neo.test.dkruppa.timelog.config;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Component which store availability status of data source
 */
@Component
public class DataSourceStatus {
    private final static AtomicBoolean isAvailable = new AtomicBoolean(true);

    public void disableConnection() {
        isAvailable.set(false);
    }

    public void enableConnection() {
        isAvailable.set(true);
    }

    public boolean isDataSourceAvailable() {
        return isAvailable.get();
    }
}
