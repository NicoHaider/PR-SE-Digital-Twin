package com.example.DigitalTwin.service;

import com.example.DigitalTwin.dto.DeviceDto;
import com.example.DigitalTwin.enums.DeviceType;
import com.example.DigitalTwin.model.Device;
import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.repository.DeviceRepository;
import com.example.DigitalTwin.repository.RoomDataRepository;
import com.example.DigitalTwin.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceTest {

    @Mock
    private RoomRepository roomRepo;

    @Mock
    private RoomDataRepository roomDataRepo;

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService deviceService;

    @InjectMocks
    private DeviceDataService deviceDataService;

    @Test
    public void testGetDevice() {
        Room room = new Room();
        room.setId(1L);

        Device device = new Device();
        device.setId(1L);
        device.setName("Thermostat");
        device.setDeviceType(DeviceType.Light);
        device.setRoom(room);

        when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));

        DeviceDto foundDevice = deviceService.getDevice(1L);

        assertNotNull(foundDevice);
        assertEquals("Thermostat", foundDevice.getName());
        assertEquals(DeviceType.Light, foundDevice.getDeviceType());
        verify(deviceRepository).findById(1L);
    }

    @Test
    public void testUpdateDevice() {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setId(1L);
        deviceDto.setName("Thermostat");
        deviceDto.setDeviceType(DeviceType.Light);

        Room room = new Room();
        room.setId(1L);

        Device device = new Device();
        device.setId(1L);
        device.setRoom(room);

        when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));
        when(deviceRepository.save(any(Device.class))).thenAnswer(invocation -> invocation.getArgument(0));

        DeviceDto updatedDevice = deviceService.updateDevice(deviceDto);

        assertNotNull(updatedDevice);
        assertEquals("Thermostat", updatedDevice.getName());
        assertEquals(DeviceType.Light, updatedDevice.getDeviceType());
        verify(deviceRepository).findById(1L);
        verify(deviceRepository).save(any(Device.class));
    }

    public void testCreateDevice() {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setName("Thermostat");
        deviceDto.setRoomId(1L);
        deviceDto.setStatus(true);
        deviceDto.setDeviceType(DeviceType.Light);

        Room room = new Room();
        room.setId(1L);

        when(roomRepo.findById(1L)).thenReturn(Optional.of(room));
        when(deviceRepository.save(any(Device.class))).thenAnswer(invocation -> {
            Device device = invocation.getArgument(0);
            device.setId(1L);
            device.setRoom(room);
            return device;
        });

        DeviceDto createdDevice = deviceService.createDevice(deviceDto);

        assertNotNull(createdDevice);
        assertEquals("Thermostat", createdDevice.getName());
        assertEquals(DeviceType.Light, createdDevice.getDeviceType());
        verify(roomRepo).findById(1L);
        verify(deviceRepository).save(any(Device.class));
    }

    public void testUpdateDeviceStatus() {
        Room room = new Room();
        room.setId(1L);

        Device device = new Device();
        device.setId(1L);
        device.setStatus(true);
        device.setRoom(room);

        when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));
        when(deviceRepository.save(any(Device.class))).thenAnswer(invocation -> invocation.getArgument(0));

        DeviceDto updatedDevice = deviceService.updateDeviceStatus(1L);

        assertNotNull(updatedDevice);
        assertFalse(updatedDevice.getStatus());
        verify(deviceRepository).findById(1L);
        verify(deviceRepository).save(any(Device.class));
    }

    public void testDeleteDevice() {
        Room room = new Room();
        room.setId(1L);

        Device device = new Device();
        device.setId(1L);
        device.setRoom(room);

        when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));

        boolean result = deviceService.deleteDevice(1L);

        assertTrue(result);
        verify(deviceRepository).findById(1L);
        verify(deviceRepository).delete(any(Device.class));
    }
}