package com.example.DigitalTwin.utils;

import com.example.DigitalTwin.model.Device;
import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.model.RoomData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CSVUtilTest {

    @Test
    public void testRoomsToCSV() {
        // Create mock RoomData
        RoomData roomData = new RoomData();
        roomData.setId(1L);
        roomData.setCo2Level(400.0);
        roomData.setTemperature(22.0);
        roomData.setNumOfPeople(5);
        roomData.setDateTime(LocalDateTime.now());

        List<RoomData> roomDataList = new ArrayList<>();
        roomDataList.add(roomData);

        // Create mock Device
        Device device = new Device();
        device.setId(1L);
        device.setName("Thermostat");
        device.setStatus(true);

        List<Device> deviceList = new ArrayList<>();
        deviceList.add(device);

        // Create mock Room
        Room room = new Room();
        room.setId(1L);
        room.setName("ConferenceRoom");
        room.setType("Office");
        room.setSize(100.0);
        room.setDevices(deviceList);
        room.setRoomDataList(roomDataList);

        List<Room> rooms = new ArrayList<>();
        rooms.add(room);

        // Convert rooms to CSV
        ByteArrayInputStream csvInputStream = CSVUtil.roomsToCSV(rooms);

        // Read and verify CSV content
        String csvContent = new String(csvInputStream.readAllBytes(), StandardCharsets.UTF_8);
        String expectedCsvContent = "Room_ID;Name;Type;Size;Device_Count;\n" +
                "1;ConferenceRoom;Office;100.0;1;\n";

        System.out.println("Actual CSV Content:");
        System.out.println(csvContent);

        System.out.println("Expected CSV Content:");
        System.out.println(expectedCsvContent);

        // Check lengths first
        assertEquals(expectedCsvContent.length(), csvContent.length(), "Lengths are different");

        // Print ASCII values for debugging
        for (int i = 0; i < Math.max(expectedCsvContent.length(), csvContent.length()); i++) {
            char expectedChar = i < expectedCsvContent.length() ? expectedCsvContent.charAt(i) : ' ';
            char actualChar = i < csvContent.length() ? csvContent.charAt(i) : ' ';
            System.out.printf("Index %d: Expected '%c' (%d), Actual '%c' (%d)%n",
                    i, expectedChar, (int) expectedChar, actualChar, (int) actualChar);
        }

        // Finally, check entire content
        assertEquals(expectedCsvContent, csvContent);
    }
}
