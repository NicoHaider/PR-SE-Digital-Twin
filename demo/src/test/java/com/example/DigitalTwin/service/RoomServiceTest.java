package com.example.DigitalTwin.service;

import com.example.DigitalTwin.dto.RoomDto;
import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
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
        Room room = new Room("Conference Room", 100.0, 1, 2, 5, 2);
        room.setDevices(new ArrayList<>());
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        Optional<RoomDto> foundRoom = Optional.ofNullable(roomService.getRoomById(1L));

        assertTrue(foundRoom.isPresent());
        assertEquals("Conference Room", foundRoom.get().getName());
        verify(roomRepository).findById(1L);
    }
}
