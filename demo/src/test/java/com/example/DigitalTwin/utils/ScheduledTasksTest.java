package com.example.DigitalTwin.utils;

import com.example.DigitalTwin.service.RoomDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.ContextConfiguration;

import java.lang.reflect.Field;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {ScheduledTasks.class})
@EnableScheduling
public class ScheduledTasksTest {

    @Mock
    private RoomDataService roomDataService;

    @InjectMocks
    private ScheduledTasks scheduledTasks;

    @BeforeEach
    public void setUp() throws Exception {
        scheduledTasks = new ScheduledTasks();
        Field roomDataServiceField = ScheduledTasks.class.getDeclaredField("roomDataService");
        roomDataServiceField.setAccessible(true);
        roomDataServiceField.set(scheduledTasks, roomDataService);
    }

    @Test
    public void testGenerateSensorData() {
        scheduledTasks.generateSensorData();
        verify(roomDataService).generateRandomRoomData();
    }
}
