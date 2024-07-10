package com.example.DigitalTwin.service;

import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.model.RoomData;
import com.example.DigitalTwin.repository.RoomRepository;
import com.example.DigitalTwin.repository.RoomDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomDataServiceTest {

    @Mock
    private RoomDataRepository roomDataRepository;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomDataService roomDataService;

    private Room room;
    private RoomData roomData;

    @BeforeEach
    public void setUp() {
        room = new Room();
        room.setId(1L);
        room.setName("Room1");

        roomData = new RoomData();
        roomData.setRoom(room);
        roomData.setCo2Level(1000);
        roomData.setTemperature(22);
        roomData.setNumOfPeople(5);
        roomData.setDateTime(LocalDateTime.now());
    }

    @Test
    public void testGenerateRandomRoomData() {
        when(roomRepository.findAll()).thenReturn(Arrays.asList(room));
        when(roomDataRepository.save(any(RoomData.class))).thenReturn(roomData);

        roomDataService.generateRandomRoomData();

        verify(roomRepository, times(1)).findAll();
        verify(roomDataRepository, times(1)).save(any(RoomData.class));
    }

    @Test
    public void testGetRoomDataByRoom() {
        List<RoomData> roomDataList = Arrays.asList(roomData);
        when(roomDataRepository.findByRoomId(1L)).thenReturn(roomDataList);

        List<RoomData> result = roomDataService.getRoomDataByRoom(1L);

        assertEquals(roomDataList.size(), result.size());
        assertEquals(roomDataList.get(0).getCo2Level(), result.get(0).getCo2Level());
        verify(roomDataRepository, times(1)).findByRoomId(1L);
    }
}
