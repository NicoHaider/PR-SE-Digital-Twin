package com.example.DigitalTwin.service;

import com.example.DigitalTwin.dto.RoomDto;
import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    @Test
    public void testGetRoomById() {
        Room room = new Room();
        room.setName("Conference Room");
        room.setSize(100.0);
        room.setDoors(1);
        room.setWindows(2);
        room.setLights(5);
        room.setFans(2);

        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        RoomDto foundRoom = roomService.getRoomById(1L);

        assertNotNull(foundRoom);
        assertEquals("Conference Room", foundRoom.getName());
        verify(roomRepository).findById(1L);
    }
}
