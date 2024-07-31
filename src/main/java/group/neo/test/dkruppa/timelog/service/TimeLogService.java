package group.neo.test.dkruppa.timelog.service;

import group.neo.test.dkruppa.timelog.model.TimeLog;

import java.util.Date;
import java.util.List;

public interface TimeLogService {
    List<TimeLog> findAll();

    TimeLog saveTimeLog(Date date);
}
