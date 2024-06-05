package com.example.DigitalTwin.service;

import com.example.DigitalTwin.dto.DeviceDto;
import com.example.DigitalTwin.model.Device;
import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.repository.DeviceRepository;
import com.example.DigitalTwin.repository.RoomRepository;
import com.example.DigitalTwin.repository.RoomDataRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

    private DeviceDto deviceDto;
    private Device device;
    private Room room;

    @BeforeEach
    public void setUp() {
        room = new Room();
        room.setId(1L);

        device = new Device();
        device.setId(1L);
        device.setName("Device1");
        device.setRoom(room);
        device.setStatus(true);
        device.setDeviceType("Sensor");
        device.setTime(new Date());

        deviceDto = new DeviceDto();
        deviceDto.setId(1L);
        deviceDto.setName("Device1");
        deviceDto.setRoomId(1L);
        deviceDto.setStatus(true);
        deviceDto.setDeviceType("Sensor");
    }

    @Test
    public void testCreateDevice() {
        when(roomRepo.findById(1L)).thenReturn(Optional.of(room));
        when(deviceRepository.save(any(Device.class))).thenReturn(device);

        DeviceDto result = deviceService.createDevice(deviceDto);

        assertNotNull(result);
        assertEquals("Device1", result.getName());
        verify(roomRepo, times(1)).findById(1L);
        verify(deviceRepository, times(1)).save(any(Device.class));
    }

    @Test
    public void testCreateDeviceRoomNotFound() {
        when(roomRepo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> deviceService.createDevice(deviceDto));
        verify(roomRepo, times(1)).findById(1L);
    }

    @Test
    public void testUpdateDeviceStatus() {
        when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));
        when(deviceRepository.save(any(Device.class))).thenReturn(device);

        DeviceDto result = deviceService.updateDeviceStatus(1L);

        assertNotNull(result);
        assertEquals("Device1", result.getName());
        verify(deviceRepository, times(1)).findById(1L);
        verify(deviceRepository, times(1)).save(any(Device.class));
    }

    @Test
    public void testUpdateDeviceStatusDeviceNotFound() {
        when(deviceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> deviceService.updateDeviceStatus(1L));
        verify(deviceRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteDevice() {
        when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));

        boolean result = deviceService.deleteDevice(1L);

        assertTrue(result);
        verify(deviceRepository, times(1)).findById(1L);
        verify(deviceRepository, times(1)).delete(any(Device.class));
    }

    @Test
    public void testDeleteDeviceNotFound() {
        when(deviceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> deviceService.deleteDevice(1L));
        verify(deviceRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetDevice() {
        when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));

        DeviceDto result = deviceService.getDevice(1L);

        assertNotNull(result);
        assertEquals("Device1", result.getName());
        verify(deviceRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetDeviceNotFound() {
        when(deviceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> deviceService.getDevice(1L));
        verify(deviceRepository, times(1)).findById(1L);
    }

    @Test
    public void testUpdateDevice() {
        when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));
        when(deviceRepository.save(any(Device.class))).thenReturn(device);

        DeviceDto result = deviceService.updateDevice(deviceDto);

        assertNotNull(result);
        assertEquals("Device1", result.getName());
        verify(deviceRepository, times(1)).findById(1L);
        verify(deviceRepository, times(1)).save(any(Device.class));
    }

    @Test
    public void testUpdateDeviceNotFound() {
        when(deviceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> deviceService.updateDevice(deviceDto));
        verify(deviceRepository, times(1)).findById(1L);
    }
}
