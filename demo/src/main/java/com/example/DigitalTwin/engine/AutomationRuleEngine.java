package com.example.DigitalTwin.engine;

import com.example.DigitalTwin.enums.DeviceType;
import com.example.DigitalTwin.model.Device;
import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.model.AutomationRule;
import com.example.DigitalTwin.model.RoomData;
import com.example.DigitalTwin.repository.DeviceRepository;
import com.example.DigitalTwin.repository.RoomRepository;
import com.example.DigitalTwin.repository.AutomationRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Component
@Transactional
public class AutomationRuleEngine {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private AutomationRuleRepository automationRuleRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    public void checkAndApplyRules() {
        List<Room> rooms = roomRepository.findAll();

        for (Room room : rooms) {
            List<AutomationRule> automationRules = automationRuleRepository.findAllByRoomId(room.getId());
            List<RoomData> roomDataList = room.getRoomDataList();
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
            case "co2_level":
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
        List<Device> devices = deviceRepository.findAllByRoomId(room.getId());
        switch (action) {
            case "open_window":
                List<Device> windows_o = devices.stream().filter(d -> d.getDeviceType() == DeviceType.Window).toList();
                windows_o.forEach(w -> w.setStatus(true));
                break;
            case "close_window":
                List<Device> windows_c = devices.stream().filter(d -> d.getDeviceType() == DeviceType.Window).toList();
                windows_c.forEach(w -> w.setStatus(false));
                break;
            case "turn_on_light":
                List<Device> lights_on = devices.stream().filter(d -> d.getDeviceType() == DeviceType.Light).toList();
                lights_on.forEach(l -> l.setStatus(true));
                break;
            case "turn_off_light":
                List<Device> lights_off = devices.stream().filter(d -> d.getDeviceType() == DeviceType.Light).toList();
                lights_off.forEach(l -> l.setStatus(false));
                break;
            case "turn_on_fan":
                List<Device> fans_on = devices.stream().filter(d -> d.getDeviceType() == DeviceType.Fan).toList();
                fans_on.forEach(f -> f.setStatus(true));
                break;
            case "turn_off_fan":
                List<Device> fans_off = devices.stream().filter(d -> d.getDeviceType() == DeviceType.Fan).toList();
                fans_off.forEach(f -> f.setStatus(false));
                break;
            case "lock_door":
                List<Device> doors_l = devices.stream().filter(d -> d.getDeviceType() == DeviceType.Door).toList();
                doors_l.forEach(d -> d.setStatus(false));
                break;
            case "unlock_door":
                List<Device> doors_u = devices.stream().filter(d -> d.getDeviceType() == DeviceType.Door).toList();
                doors_u.forEach(d -> d.setStatus(true));
                break;
            default:
                break;
        }
        deviceRepository.saveAll(devices);
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
