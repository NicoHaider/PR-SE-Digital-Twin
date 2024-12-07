package com.example.DigitalTwin.model;

import com.example.DigitalTwin.dto.DeviceDto;
import com.example.DigitalTwin.enums.DeviceType;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class DeviceTest {

    @Test
    public void testGettersAndSetters() {
        Device device = new Device();
        Room room = new Room();
        room.setId(1L);
        Date now = new Date();

        device.setId(1L);
        device.setName("Thermostat");
        device.setStatus(true);
        device.setTime(now);
        device.setDeviceType(DeviceType.Light);
        device.setRoom(room);

        assertEquals(1L, device.getId());
        assertEquals("Thermostat", device.getName());
        assertTrue(device.getStatus());
        assertEquals(now, device.getTime());
        assertEquals(DeviceType.Light, device.getDeviceType());
        assertEquals(room, device.getRoom());
    }

    @Test
    public void testGetDto() {
        Device device = new Device();
        Room room = new Room();
        room.setId(1L);
        Date now = new Date();

        device.setId(1L);
        device.setName("Thermostat");
        device.setStatus(true);
        device.setTime(now);
        device.setDeviceType(DeviceType.Light);
        device.setRoom(room);

        DeviceDto deviceDto = device.getDto();

        assertEquals(1L, deviceDto.getId());
        assertEquals("Thermostat", deviceDto.getName());
        assertTrue(deviceDto.getStatus());
        assertEquals(now, deviceDto.getTime());
        assertEquals(DeviceType.Light, deviceDto.getDeviceType());
        assertEquals(1L, deviceDto.getRoomId());
    }

    @Test
    public void testNoRoom() {
        Device device = new Device();
        Date now = new Date();
        Room room = new Room();
        room.setId(1L);

        device.setId(1L);
        device.setName("Thermostat");
        device.setStatus(true);
        device.setTime(now);
        device.setDeviceType(DeviceType.Light);
        device.setRoom(room);

        DeviceDto deviceDto = device.getDto();

        assertEquals(1L, deviceDto.getId());
        assertEquals("Thermostat", deviceDto.getName());
        assertTrue(deviceDto.getStatus());
        assertEquals(now, deviceDto.getTime());
        assertEquals(DeviceType.Light, deviceDto.getDeviceType());
        assertEquals(1L, deviceDto.getRoomId());
    }


}
