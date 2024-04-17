package com.example.DigitalTwin.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.example.DigitalTwin.dto.RoomDto;
import com.example.DigitalTwin.model.Room;

@DataJpaTest
@Import(RoomService.class)
public class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    @Test
    void testCreateRoom() {
        RoomDto room = new RoomDto();
        room.setName("TEST ROOM");
		room.setSize(20.0);
		room.setDoors(1);
		room.setWindows(4);
		room.setLights(20);
		room.setFans(4);
        Room savedRoom = roomService.createRoom(room);

        assertThat(savedRoom).isNotNull();
        assertThat(savedRoom.getName()).isEqualTo("Test Room"); // Stellt sicher, dass der Name korrekt gesetzt und gespeichert wurde
    }
}
