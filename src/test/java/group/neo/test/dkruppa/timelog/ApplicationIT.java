package group.neo.test.dkruppa.timelog;

import com.github.dockerjava.api.DockerClient;
import group.neo.test.dkruppa.timelog.dto.TimeLogDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureWebTestClient
@Testcontainers
class ApplicationIT {

    static final MySQLContainer MY_SQL_CONTAINER;

    @Autowired
    WebTestClient webTestClient;

    static {
        MY_SQL_CONTAINER = new MySQLContainer("mysql:latest");
        MY_SQL_CONTAINER.start();
    }

    @DynamicPropertySource
    static void configureTestProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> MY_SQL_CONTAINER.getJdbcUrl());
        registry.add("spring.datasource.username", () -> MY_SQL_CONTAINER.getUsername());
        registry.add("spring.datasource.password", () -> MY_SQL_CONTAINER.getPassword());
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
    }

    @Test
    void saveOrderEntity() throws InterruptedException {
        List<TimeLogDTO> timeLogsBeforeDBConnLost = getTimeLogsFromServer();

        //Simulate connection lost
        DockerClient dockerClient = DockerClientFactory.lazyClient();
        String tag = MY_SQL_CONTAINER.getContainerId();
        dockerClient.pauseContainerCmd(tag).exec();
        TimeUnit.SECONDS.sleep(6);
        dockerClient.unpauseContainerCmd(tag).exec();

        TimeUnit.SECONDS.sleep(6);
        List<TimeLogDTO> timeLogsAfterDBConnLost = getTimeLogsFromServer();

        assertThat(timeLogsAfterDBConnLost.containsAll(timeLogsBeforeDBConnLost)).isTrue();
        TimeLogDTO previous = timeLogsAfterDBConnLost.get(0);
        for (int i = 1; i < timeLogsAfterDBConnLost.size(); i++) {
            TimeLogDTO current = timeLogsAfterDBConnLost.get(i);
            assertThat(current.getDateTime().getTime()).isGreaterThan(previous.getDateTime().getTime());
            long msBetween = (current.getDateTime().getTime() - previous.getDateTime().getTime());
            assertThat(msBetween < 2000 && msBetween >= 1000).isTrue();
            previous = current;
        }
    }

    private List<TimeLogDTO> getTimeLogsFromServer() {
        List<TimeLogDTO> result = new ArrayList<>();
        webTestClient.get()
                .uri("/time-log")
                .exchange()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(TimeLogDTO.class)
                .consumeWith(listOfObject -> {
                    List<TimeLogDTO> list = listOfObject.getResponseBody();
                    assert list != null;
                    result.addAll(list);
                });
        return result;
    }
}
