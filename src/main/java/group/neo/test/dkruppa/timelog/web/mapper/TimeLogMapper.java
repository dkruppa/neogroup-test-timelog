package group.neo.test.dkruppa.timelog.web.mapper;

import group.neo.test.dkruppa.timelog.dto.TimeLogDTO;
import group.neo.test.dkruppa.timelog.model.TimeLog;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TimeLogMapper {
    List<TimeLogDTO> map(List<TimeLog> employees);
}

