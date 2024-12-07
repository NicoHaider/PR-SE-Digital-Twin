package com.example.DigitalTwin.service;

import com.example.DigitalTwin.enums.DeviceType;
import com.example.DigitalTwin.model.Device;
import com.example.DigitalTwin.model.DeviceData;
import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.repository.DeviceDataRepository;
import com.example.DigitalTwin.repository.RoomRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeviceDataService {

    @Autowired
    private DeviceDataRepository deviceDataRepository;

    @Autowired
    RoomRepository roomRepo;

	private static final Logger logger = LogManager.getLogger(DeviceDataService.class);

    public List<DeviceData> getRoomDataByRoom(Long roomId) {
        return deviceDataRepository.findByRoomId(roomId);
    }

    public DeviceData addDeviceData(Room room) {
        DeviceData deviceData = new DeviceData();
        deviceData.setDateTime(LocalDateTime.now());
        deviceData.setRoom(room);

        int numOnWindows = 0;
        int numOnDoors = 0;
        int numOnLights = 0;
        int numOnFans = 0;

        logger.info("Devices got in room: " + room.getId());
        for (Device device : room.getDevices()) {
            logger.info("Device: " + device.getName() + " " + device.getDeviceType() + " " + device.getStatus());
            if (device.getDeviceType().equals(DeviceType.Window) && device.getStatus()) {
            numOnWindows++;
            } else if (device.getDeviceType().equals(DeviceType.Door) && device.getStatus()) {
            numOnDoors++;
            } else if (device.getDeviceType().equals(DeviceType.Light) && device.getStatus()) {
            numOnLights++;
            } else if (device.getDeviceType().equals(DeviceType.Fan) && device.getStatus()) {
            numOnFans++;
            }
        }
        deviceData.setWindows_on(numOnWindows);
        deviceData.setDoors_on(numOnDoors);
        deviceData.setLights_on(numOnLights);
        deviceData.setFans_on(numOnFans);

        return deviceDataRepository.save(deviceData);
        
    }
}
