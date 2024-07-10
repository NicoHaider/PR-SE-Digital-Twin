package com.example.DigitalTwin.service;


import com.example.DigitalTwin.dto.DeviceDto;
import com.example.DigitalTwin.model.Device;
import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.repository.DeviceRepository;
import com.example.DigitalTwin.repository.RoomDataRepository;
import com.example.DigitalTwin.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Service
public class DeviceService {
    private static final Logger logger = LogManager.getLogger(String.valueOf(DeviceService.class));

    @Autowired
    RoomRepository roomRepo;

    @Autowired
    RoomDataRepository roomDataRepo;

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
	DeviceDataService deviceDataService;

    public DeviceDto createDevice(DeviceDto deviceDto) {
        logger.info("Request to create device: " + deviceDto);
        Optional<Room> optionalRoom = roomRepo.findById(deviceDto.getRoomId());
        if(optionalRoom.isPresent()){
            Room room = optionalRoom.get();
            Device device = new Device();
            device.setName(deviceDto.getName());
            device.setRoom(room);
            device.setStatus(deviceDto.getStatus());
            device.setDeviceType(deviceDto.getDeviceType());
            device.setTime(new Date());

            DeviceDto createdDevice = deviceRepository.save(device).getDto();
            logger.info("Device created successfully: " + createdDevice);
            return createdDevice;
        } else {
            logger.error("Room not found with id: "+deviceDto.getRoomId());
            throw new EntityNotFoundException("Room not present");
        }
    }

    public DeviceDto updateDeviceStatus(Long deviceId) {
        logger.info("Request to update status for device id: "+deviceId);
        Optional<Device> optionalDevice = deviceRepository.findById(deviceId);
        if(optionalDevice.isPresent()){
            Device device = optionalDevice.get();

            device.setStatus(!device.getStatus());

            DeviceDto updatedDevice = deviceRepository.save(device).getDto();
            logger.info("Device status updated successfully: "+updatedDevice);
            return updatedDevice;
        } else {
            logger.error("Device not found with id: "+deviceId);
            throw new EntityNotFoundException("Device not present");
        }
    }

    public boolean deleteDevice(Long deviceId) {
        logger.info("request to delete device with id: "+deviceId);
        Optional<Device> optionalDevice = deviceRepository.findById(deviceId);
        if(optionalDevice.isPresent()){
            deviceRepository.delete(optionalDevice.get());
            logger.info("Device with id: "+deviceId+" deleted successfully.");
            return true;
        } else {
            logger.error("Device with id: "+deviceId+"not found.");
            throw new EntityNotFoundException("Device not present");
        }
    }

    public DeviceDto getDevice(Long deviceId) {
        logger.info("Request to get device with id: "+deviceId);
        Optional<Device> optionalDevice = deviceRepository.findById(deviceId);
        if(optionalDevice.isPresent()){
            DeviceDto deviceDto = optionalDevice.get().getDto();
            logger.info("Device retrieved successfully: "+ deviceDto);
            return deviceDto;
        } else {
            logger.error("Device not found with id: " + deviceId);
            throw new EntityNotFoundException("Device not present");
        }
    }

    public DeviceDto updateDevice(DeviceDto deviceDto) {
       /* Optional<Room> optionalRoom = roomRepo.findById(deviceDto.getRoomId());
        if(optionalRoom.isPresent()){
            Device device = deviceRepository.findById().get();
            System.out.println(device.getId());
            device.setName(deviceDto.getName());
            device.setRoom(optionalRoom.get());
            device.setStatus(deviceDto.getStatus());
            device.setDeviceType(deviceDto.getDeviceType());
            System.out.println(device);
            return deviceRepository.save(device).getDto();
        }else{
            throw new EntityNotFoundException("Room Not present");
        }*/ 

        logger.info("Request to update device: " + deviceDto);
        Optional<Device> optionalDevice = deviceRepository.findById(deviceDto.getId());
        if(optionalDevice.isPresent()){
            Device device = optionalDevice.get();

            device.setName(deviceDto.getName());
            device.setDeviceType(deviceDto.getDeviceType());
            DeviceDto updatedDevice = deviceRepository.save(device).getDto();
            logger.info("Device updated successfully: " + updatedDevice);
            return updatedDevice;
        } else {
            logger.error("Device with id: "+deviceDto.getId() + " not found.");
            throw new EntityNotFoundException("Device not present");
        }
    }
}
