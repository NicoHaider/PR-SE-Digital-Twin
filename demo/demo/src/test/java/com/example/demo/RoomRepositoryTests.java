package com.example.demo;

import com.example.demo.modell.Room;
import com.example.demo.repository.RoomRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class RoomRepositoryTests {

    @Autowired
    private RoomRepository repository;

    @Test
    public void testCreateRoom() {
        Room room = new Room();
        room.setSize(25);
        room.setDoors(1);
        room.setWindows(3);
        room.setLights(5);
        room.setFans(1);
        Room savedRoom = repository.save(room);

        assertThat(savedRoom).isNotNull();
        assertThat(savedRoom.getId()).isNotNull();
        // Weitere Assertions können hier hinzugefügt werden, um die gespeicherten Daten zu überprüfen
    }

    private <SELF extends Assert<SELF, Object>> Assert<SELF, Object> assertThat(Room savedRoom) {
        return null;
    }
}