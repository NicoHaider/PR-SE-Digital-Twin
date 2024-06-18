package com.example.DigitalTwin.model;

import com.example.DigitalTwin.dto.RoomDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    @Test
    public void testGettersAndSetters() {
        Room room = new Room();
        room.setId(1L);
        room.setName("Conference Room");
        room.setSize(100.0);
        room.setType("Office");

        assertEquals(1L, room.getId());
        assertEquals("Conference Room", room.getName());
        assertEquals(100.0, room.getSize());
        assertEquals("Office", room.getType());
    }

    @Test
    public void testGetDto() {
        Room room = new Room();
        room.setId(1L);
        room.setName("Conference Room");
        room.setSize(100.0);
        room.setType("Office");

        RoomDto roomDto = room.getDto();

        assertEquals(1L, roomDto.getId());
        assertEquals("Conference Room", roomDto.getName());
        assertEquals(100.0, roomDto.getSize());
        assertEquals("Office", roomDto.getType());
    }

    @Test
    public void testDevices() {
        Room room = new Room();
        List<Device> devices = new ArrayList<>();
        Device device1 = new Device();
        device1.setId(1L);
        device1.setName("Thermostat");
        device1.setRoom(room);

        Device device2 = new Device();
        device2.setId(2L);
        device2.setName("Light");
        device2.setRoom(room);

        devices.add(device1);
        devices.add(device2);

        room.setDevices(devices);

        assertEquals(2, room.getDevices().size());
        assertEquals("Thermostat", room.getDevices().get(0).getName());
        assertEquals("Light", room.getDevices().get(1).getName());
    }
}
