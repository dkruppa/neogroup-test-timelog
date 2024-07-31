package group.neo.test.dkruppa.timelog.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
@EqualsAndHashCode
public class TimeLogDTO {
    private Long id;

    private Date dateTime;
}
