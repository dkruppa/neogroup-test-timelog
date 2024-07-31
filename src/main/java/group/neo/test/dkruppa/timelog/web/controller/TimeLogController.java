package group.neo.test.dkruppa.timelog.web.controller;

import group.neo.test.dkruppa.timelog.dto.TimeLogDTO;
import group.neo.test.dkruppa.timelog.model.TimeLog;
import group.neo.test.dkruppa.timelog.service.TimeLogService;
import group.neo.test.dkruppa.timelog.web.mapper.TimeLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("time-log")
@RestController
public class TimeLogController {

    private final TimeLogService timeLogService;
    private final TimeLogMapper timeLogMapper;

    @GetMapping
    public ResponseEntity<List<TimeLogDTO>> getTimeLog() {
        List<TimeLog> timeLogList = timeLogService.findAll();
        List<TimeLogDTO> timeLogDTOList = timeLogMapper.map(timeLogList);
        return new ResponseEntity<>(timeLogDTOList, HttpStatus.OK);
    }
}
