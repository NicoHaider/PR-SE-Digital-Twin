package com.example.DigitalTwin.service;


import com.example.DigitalTwin.dto.DeviceDto;
import com.example.DigitalTwin.dto.RoomDto;
import com.example.DigitalTwin.model.Device;
import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.repository.DeviceRepository;
import com.example.DigitalTwin.repository.RoomDataRepository;
import com.example.DigitalTwin.repository.RoomRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    RoomRepository roomRepo;

    @Autowired
    RoomDataRepository roomDataRepo;

    @Autowired
    DeviceRepository deviceRepository;

    public DeviceDto createDevice(DeviceDto deviceDto) {
        Optional<Room> optionalRoom = roomRepo.findById(deviceDto.getRoomId());
        if(optionalRoom.isPresent()){
            Device device = new Device();
            device.setName(deviceDto.getName());
            device.setRoom(optionalRoom.get());
            device.setStatus(deviceDto.getStatus());
            device.setDeviceType(deviceDto.getDeviceType());
            device.setTime(new Date());

            return deviceRepository.save(device).getDto();
        }else{
            throw new EntityNotFoundException("Room Not present");
        }
    }

    public DeviceDto updateDeviceStatus(Long deviceId) {
        Optional<Device> optionalDevice = deviceRepository.findById(deviceId);
        if(optionalDevice.isPresent()){
            Device device = optionalDevice.get();

            device.setStatus(!device.getStatus());

            return deviceRepository.save(device).getDto();
        }else{
            throw new EntityNotFoundException("Device Not present");
        }
    }

    public boolean deleteDevice(Long deviceId) {
        Optional<Device> optionalDevice = deviceRepository.findById(deviceId);
        if(optionalDevice.isPresent()){
            deviceRepository.delete(optionalDevice.get());
            return true;
        }else{
            throw new EntityNotFoundException("Device Not present");
        }
    }

    public DeviceDto getDevice(Long deviceId) {
        Optional<Device> optionalDevice = deviceRepository.findById(deviceId);
        if(optionalDevice.isPresent()){
            return optionalDevice.get().getDto();
        }else{
            throw new EntityNotFoundException("Device Not present");
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

        Optional<Device> optionalDevice = deviceRepository.findById(deviceDto.getId());
        if(optionalDevice.isPresent()){
            Device device = optionalDevice.get();

            device.setName(deviceDto.getName());
            device.setDeviceType(deviceDto.getDeviceType());

            return deviceRepository.save(device).getDto();
        }else{
            throw new EntityNotFoundException("Device Not present");
        }

        
    }
    
}
