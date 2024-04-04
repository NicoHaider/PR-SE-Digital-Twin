package com.example.DigitalTwin.service;

import com.example.DigitalTwin.model.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(RoomService.class)
public class RoomServiceTest {

    @Autowired
    private RoomService roomService;

    @Test
    void testCreateRoom() {
        Room room = new Room("Test Room", 20.0, 1, 2, 3, 4);
        Room savedRoom = roomService.saveOrUpdateRoom(room);

        assertThat(savedRoom).isNotNull();
        assertThat(savedRoom.getName()).isEqualTo("Test Room"); // Stellt sicher, dass der Name korrekt gesetzt und gespeichert wurde
    }
}
