package group.neo.test.dkruppa.timelog.service;

import java.util.Date;

public interface TimeLogQueueService {

    void addToQueue(Date date);

    Date getFromQueue();

    boolean isEmpty();
}
