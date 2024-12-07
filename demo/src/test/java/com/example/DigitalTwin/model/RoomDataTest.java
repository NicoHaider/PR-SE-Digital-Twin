package com.example.DigitalTwin.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class RoomDataTest {

    @Test
    public void testGettersAndSetters() {
        RoomData roomData = new RoomData();
        Room room = new Room();
        room.setId(1L);
        LocalDateTime now = LocalDateTime.now();

        roomData.setId(1L);
        roomData.setCo2Level(400.0);
        roomData.setTemperature(22.0);
        roomData.setNumOfPeople(5);
        roomData.setDateTime(now);
        roomData.setRoom(room);

        assertEquals(1L, roomData.getId());
        assertEquals(400.0, roomData.getCo2Level());
        assertEquals(22.0, roomData.getTemperature());
        assertEquals(5, roomData.getNumOfPeople());
        assertEquals(now, roomData.getDateTime());
        assertEquals(room, roomData.getRoom());
    }

    @Test
    public void testRoomNull() {
        RoomData roomData = new RoomData();
        LocalDateTime now = LocalDateTime.now();

        roomData.setId(1L);
        roomData.setCo2Level(400.0);
        roomData.setTemperature(22.0);
        roomData.setNumOfPeople(5);
        roomData.setDateTime(now);
        roomData.setRoom(null);  // Set room to null

        assertEquals(1L, roomData.getId());
        assertEquals(400.0, roomData.getCo2Level());
        assertEquals(22.0, roomData.getTemperature());
        assertEquals(5, roomData.getNumOfPeople());
        assertEquals(now, roomData.getDateTime());
        assertNull(roomData.getRoom());  // Expect room to be null
    }
}
