package com.example.DigitalTwin.utils;

import com.example.DigitalTwin.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    @Autowired
    private SensorDataService sensorDataService;

    @Scheduled(fixedRate = 10000) //millisekunden
    public void generateSensorData() {
        sensorDataService.generateRandomSensorData();
    }
}
