package com.example.DigitalTwin.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoorDtoTest {

    @Test
    public void testGettersAndSetters() {
        DoorDto doorDto = new DoorDto();

        doorDto.setDoorID(1);
        doorDto.setName("Front Door");

        assertEquals(1, doorDto.getDoorID());
        assertEquals("Front Door", doorDto.getName());
    }

    @Test
    public void testDefaultValues() {
        DoorDto doorDto = new DoorDto();

        assertEquals(0, doorDto.getDoorID()); // default int value
        assertNull(doorDto.getName()); // default String value is null
    }

    @Test
    public void testSetNullName() {
        DoorDto doorDto = new DoorDto();
        doorDto.setName(null);

        assertNull(doorDto.getName());
    }

    @Test
    public void testNegativeDoorID() {
        DoorDto doorDto = new DoorDto();
        doorDto.setDoorID(-1);

        assertEquals(-1, doorDto.getDoorID());
    }

    @Test
    public void testSetEmptyName() {
        DoorDto doorDto = new DoorDto();
        doorDto.setName("");

        assertEquals("", doorDto.getName());
    }

    @Test
    public void testSetLongName() {
        DoorDto doorDto = new DoorDto();
        String longName = "A very long door name that exceeds typical expectations for a door name";
        doorDto.setName(longName);

        assertEquals(longName, doorDto.getName());
    }
}
