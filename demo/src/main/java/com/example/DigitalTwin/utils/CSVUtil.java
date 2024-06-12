package com.example.DigitalTwin.utils;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;


import com.example.DigitalTwin.model.Room;

public class CSVUtil {

    public static ByteArrayInputStream roomsToCSV(List<Room> rooms) {
        StringBuilder csvContent = new StringBuilder();
        csvContent.append("Room_ID;Name;Type;Size;Device_Count;\n");

        for (Room room : rooms) {
            csvContent.append(room.getId()).append(";")
                    .append(room.getName()).append(";")
                    .append(room.getType()).append(";")
                    .append(room.getSize()).append(";")
                    .append(room.getDevices().size()).append(";");
        }

        return new ByteArrayInputStream(csvContent.toString().getBytes(StandardCharsets.UTF_8));
    }
}