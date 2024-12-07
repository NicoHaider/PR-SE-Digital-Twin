package com.example.DigitalTwin.utils;

import com.example.DigitalTwin.engine.AutomationRuleEngine;
import com.example.DigitalTwin.service.RoomDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    @Autowired
    private RoomDataService roomDataService;

    @Autowired
    private AutomationRuleEngine automationRuleEngine;

    @Scheduled(fixedRate = 15000) //millisekunden
    public void generateSensorData() {
        roomDataService.generateRandomRoomData();
    }

    @Scheduled(fixedRate = 2000)
    public void applyAutomationRules() {
        automationRuleEngine.checkAndApplyRules();

    }
}
