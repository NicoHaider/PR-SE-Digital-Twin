package com.example.DigitalTwin.model;

import com.example.DigitalTwin.dto.RoomDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    private Room room;

    @BeforeEach
    public void setUp() {
        room = new Room();
    }

    @Test
    public void testConstructorWithArguments() {
        Room room = new Room("Conference Room", 100.0, 1, 2, 3, 4, 22.5, 400.0, 5);
        assertEquals("Conference Room", room.getName());
        assertEquals(100.0, room.getSize());
        assertEquals(1, room.getDoors());
        assertEquals(2, room.getWindows());
        assertEquals(3, room.getLights());
        assertEquals(4, room.getFans());
        assertEquals(22.5, room.getTemperature());
        assertEquals(400.0, room.getCo2());
        assertEquals(5, room.getPeopleCount());
    }

    @Test
    public void testGettersAndSetters() {
        room.setId(1L);
        room.setName("Conference Room");
        room.setSize(100.0);
        room.setDoors(1);
        room.setWindows(2);
        room.setLights(3);
        room.setFans(4);
        room.setTemperature(22.5);
        room.setCo2(400.0);
        room.setPeopleCount(5);
        room.setType("Office");

        assertEquals(1L, room.getId());
        assertEquals("Conference Room", room.getName());
        assertEquals(100.0, room.getSize());
        assertEquals(1, room.getDoors());
        assertEquals(2, room.getWindows());
        assertEquals(3, room.getLights());
        assertEquals(4, room.getFans());
        assertEquals(22.5, room.getTemperature());
        assertEquals(400.0, room.getCo2());
        assertEquals(5, room.getPeopleCount());
        assertEquals("Office", room.getType());
    }

    @Test
    public void testGetDto() {
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

    @Test
    public void testRoomDataList() {
        List<RoomData> roomDataList = new ArrayList<>();
        RoomData roomData1 = new RoomData();
        roomData1.setId(1L);
        roomData1.setRoom(room);

        RoomData roomData2 = new RoomData();
        roomData2.setId(2L);
        roomData2.setRoom(room);

        roomDataList.add(roomData1);
        roomDataList.add(roomData2);

        room.setRoomDataList(roomDataList);

        assertEquals(2, room.getRoomDataList().size());
        assertEquals(1L, room.getRoomDataList().get(0).getId());
        assertEquals(2L, room.getRoomDataList().get(1).getId());
    }

    @Test
    public void testEmptyRoomDataList() {
        room.setRoomDataList(new ArrayList<>());
        assertNotNull(room.getRoomDataList());
        assertTrue(room.getRoomDataList().isEmpty());
    }

    @Test
    public void testEmptyDevicesList() {
        room.setDevices(new ArrayList<>());
        assertNotNull(room.getDevices());
        assertTrue(room.getDevices().isEmpty());
    }

    @Test
    public void testRoomDtoConversionWithDevices() {
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

        RoomDto roomDto = room.getDto();

        assertEquals(2, roomDto.getDeviceDtoList().size());
        assertEquals("Thermostat", roomDto.getDeviceDtoList().get(0).getName());
        assertEquals("Light", roomDto.getDeviceDtoList().get(1).getName());
    }
}
