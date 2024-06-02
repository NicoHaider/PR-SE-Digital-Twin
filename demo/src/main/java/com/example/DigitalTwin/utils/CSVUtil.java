package com.example.DigitalTwin.utils;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import com.example.DigitalTwin.model.Room;

public class CSVUtil {

    public static ByteArrayInputStream roomsToCSV(List<Room> rooms) {
        StringBuilder csvContent = new StringBuilder();
        csvContent.append("Room_ID,Name,Type,Size,Device_Count,Climate_Data_Description\n");

        for (Room room : rooms) {
            String climateDataDescription = room.getRoomDataList().stream()
                    .map(data -> data.toString())  // Assuming RoomData has a meaningful toString implementation
                    .collect(Collectors.joining("; "));

            csvContent.append(room.getId()).append(",")
                    .append(room.getName()).append(",")
                    .append(room.getType()).append(",")
                    .append(room.getSize()).append(",")
                    .append(room.getDevices().size()).append(",")
                    .append(climateDataDescription).append("\n");
        }

        return new ByteArrayInputStream(csvContent.toString().getBytes(StandardCharsets.UTF_8));
    }
}
