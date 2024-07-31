package group.neo.test.dkruppa.timelog.repository;

import group.neo.test.dkruppa.timelog.model.TimeLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeLogRepository extends JpaRepository<TimeLog, Long> {
}
