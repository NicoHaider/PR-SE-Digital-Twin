package com.example.DigitalTwin.dto;

//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
public class DoorDto {

    private int doorID;

    private String name;

    public int getDoorID() {
        return doorID;
    }

    public void setDoorID(int doorID) {
        this.doorID = doorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*
    @Test
    public void testGettersAndSetters() {
        DoorDto doorDto = new DoorDto();

        doorDto.setDoorID(123);
        doorDto.setName("Main Entrance");

        assertEquals(123, doorDto.getDoorID());
        assertEquals("Main Entrance", doorDto.getName());
    }

    @Test
    public void testDoorID() {
        DoorDto doorDto = new DoorDto();

        doorDto.setDoorID(456);
        assertEquals(456, doorDto.getDoorID());
    }

    @Test
    public void testName() {
        DoorDto doorDto = new DoorDto();

        doorDto.setName("Side Door");
        assertEquals("Side Door", doorDto.getName());
    }*/
}
