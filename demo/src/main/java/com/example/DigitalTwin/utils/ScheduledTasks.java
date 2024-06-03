package com.example.DigitalTwin.utils;

import com.example.DigitalTwin.service.RoomDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    @Autowired
    private RoomDataService roomDataService;

    @Scheduled(fixedRate = 10000) //millisekunden
    public void generateSensorData() {
        roomDataService.generateRandomRoomData();
    }
}
