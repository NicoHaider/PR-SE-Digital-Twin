package com.example.DigitalTwin.engine;

import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.model.AutomationRule;
import com.example.DigitalTwin.repository.RoomRepository;
import com.example.DigitalTwin.repository.AutomationRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class AutomationRuleEngine {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RuleRepository ruleRepository;

    @Scheduled(fixedRate = 5000)
    public void checkRules() {
        List<Room> rooms = roomRepository.findAll();
        List<AutomationRule> automationRules = automationRuleRepository.findAll();

        //feste Regeln, wird alle 5 sek geprüft und durchgeführt
        for (Room room : rooms) {
            if (room.getPeopleCount() == 0) {
                closeAllWindows(room);
                turnOffAllLights(room);
            }

            for (AutomationRule automationRule : automationRules) {
                if (evaluateCondition(automationRule.getCondition(), room)) {
                    executeAction(automationRule.getAction(), room);
                }
            }
        }
    }

    private boolean evaluateCondition(String condition, Room room) {
        if (condition == null || condition.isEmpty()) {
            return true;
        }

        String[] conditionParts = condition.split(" ");
        if (conditionParts.length != 3) {
            return false;
        }

        String parameter = conditionParts[0];
        String operator = conditionParts[1];
        String value = conditionParts[2];

        switch (parameter) {
            case "temperature":
                return evaluateNumericCondition(room.getTemperature(), operator, Double.parseDouble(value));
            case "co2":
                return evaluateNumericCondition(room.getCo2(), operator, Double.parseDouble(value));
            case "peopleCount":
                return evaluateNumericCondition(room.getPeopleCount(), operator, Integer.parseInt(value));
            case "time":
                return evaluateTimeCondition(LocalTime.now(), operator, value);
            default:
                return false;
        }
    }

    private boolean evaluateNumericCondition(double roomValue, String operator, double conditionValue) {
        switch (operator) {
            case ">":
                return roomValue > conditionValue;
            case "<":
                return roomValue < conditionValue;
            case "=":
                return roomValue == conditionValue;
            default:
                return false;
        }
    }

    private boolean evaluateTimeCondition(LocalTime currentTime, String operator, String conditionTime) {
        LocalTime time = LocalTime.parse(conditionTime);
        switch (operator) {
            case ">":
                return currentTime.isAfter(time);
            case "<":
                return currentTime.isBefore(time);
            default:
                return false;
        }
    }

    private void executeAction(String action, Room room) {
        switch (action) {
            case "open_window":
                room.setWindows(room.getWindows() + 1);
                break;
            case "close_window":
                room.setWindows(Math.max(0, room.getWindows() - 1));
                break;
            case "turn_on_light":
                room.setLights(room.getLights() + 1);
                break;
            case "turn_off_light":
                room.setLights(Math.max(0, room.getLights() - 1));
                break;
            case "turn_on_fan":
                room.setFans(room.getFans() + 1);
                break;
            case "turn_off_fan":
                room.setFans(Math.max(0, room.getFans() - 1));
                break;
            case "lock_door":
                room.setDoors(room.getDoors() + 1);
                break;
            case "unlock_door":
                room.setDoors(Math.max(0, room.getDoors() - 1));
                break;
            default:
                break;
        }
        roomRepository.save(room);
    }

    private void closeAllWindows(Room room) {
        room.setWindows(0);
        roomRepository.save(room);
    }

    private void turnOffAllLights(Room room) {
        room.setLights(0);
        roomRepository.save(room);
    }
}
