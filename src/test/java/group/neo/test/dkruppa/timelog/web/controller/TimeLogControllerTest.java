package group.neo.test.dkruppa.timelog.web.controller;

import group.neo.test.dkruppa.timelog.model.TimeLog;
import group.neo.test.dkruppa.timelog.service.TimeLogService;
import group.neo.test.dkruppa.timelog.web.mapper.TimeLogMapperImpl;
import group.neo.test.dkruppa.timelog.web.mapper.TimeLogMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TimeLogController.class)
class TimeLogControllerTest {
    @MockBean
    private TimeLogService timeLogService;
    @Autowired
    private MockMvc mvc;

    @TestConfiguration
    static class AdditionalConfig {
        @Bean
        public TimeLogMapper productValidator() {
            return new TimeLogMapperImpl();
        }
    }

    @Test
    public void getAllEmployeesAPI() throws Exception {
        //Given
        List<TimeLog> timeLogList = Arrays.asList(new TimeLog(1L, new Date()),
                new TimeLog(2L, new Date()));
        //When
        when(timeLogService.findAll()).thenReturn(timeLogList);

        //Then
        mvc.perform(MockMvcRequestBuilders.get("/time-log"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(timeLogList.size()))
                .andDo(print());
    }
}
