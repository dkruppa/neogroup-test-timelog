package group.neo.test.dkruppa.timelog.config;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DataSourceStatusTest {
    DataSourceStatus dataSourceStatus = new DataSourceStatus();

    @Test
    void shouldSuccessfulDisableConnection() {
        // Given
        assertThat(dataSourceStatus.isDataSourceAvailable()).isTrue();
        // When
        dataSourceStatus.disableConnection();
        // Then
        assertThat(dataSourceStatus.isDataSourceAvailable()).isFalse();
    }

    @Test
    void shouldSuccessfulEnableConnection() {
        // Given
        dataSourceStatus.disableConnection();
        assertThat(dataSourceStatus.isDataSourceAvailable()).isFalse();
        // When
        dataSourceStatus.enableConnection();
        // Then
        assertThat(dataSourceStatus.isDataSourceAvailable()).isTrue();
    }
}
