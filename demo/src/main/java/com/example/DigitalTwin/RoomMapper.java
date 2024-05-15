package com.example.DigitalTwin;

import com.example.DigitalTwin.dto.RoomDto;
import com.example.DigitalTwin.model.Room;

public class RoomMapper {

    public static Room toEntity(RoomDto roomDto) {
        return new Room(
                roomDto.getName(),
                roomDto.getSize(),
                roomDto.getDoors(),
                roomDto.getWindows(),
                roomDto.getLights(),
                roomDto.getFans()
        );
    }

    public static RoomDto toDto(Room room) {
        RoomDto dto = new RoomDto();
        dto.setName(room.getName());
        dto.setSize(room.getSize());
        dto.setDoors(room.getDoors());
        dto.setWindows(room.getWindows());
        dto.setLights(room.getLights());
        dto.setFans(room.getFans());
        return dto;
    }
}