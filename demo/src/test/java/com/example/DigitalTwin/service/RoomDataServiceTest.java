package com.example.DigitalTwin.service;

import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.model.RoomData;
import com.example.DigitalTwin.repository.RoomRepository;
import com.example.DigitalTwin.repository.RoomDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomDataServiceTest {

    @Mock
    private RoomDataRepository roomDataRepository;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomDataService roomDataService;

    @Test
    public void testGenerateRandomRoomData() {
        Room room1 = new Room();
        room1.setId(1L);
        room1.setName("Conference Room");

        Room room2 = new Room();
        room2.setId(2L);
        room2.setName("Meeting Room");

        when(roomRepository.findAll()).thenReturn(Arrays.asList(room1, room2));
        when(roomDataRepository.save(any(RoomData.class))).thenAnswer(invocation -> invocation.getArgument(0));

        roomDataService.generateRandomRoomData();

        verify(roomRepository).findAll();
        verify(roomDataRepository, times(2)).save(any(RoomData.class));
    }

    @Test
    public void testGetRoomDataByRoom() {
        RoomData roomData1 = new RoomData();
        roomData1.setId(1L);
        roomData1.setCo2Level(400.0);
        roomData1.setTemperature(22.0);
        roomData1.setNumOfPeople(5);
        roomData1.setDateTime(LocalDateTime.now());

        RoomData roomData2 = new RoomData();
        roomData2.setId(2L);
        roomData2.setCo2Level(450.0);
        roomData2.setTemperature(23.0);
        roomData2.setNumOfPeople(6);
        roomData2.setDateTime(LocalDateTime.now());

        when(roomDataRepository.findByRoomId(1L)).thenReturn(Arrays.asList(roomData1, roomData2));

        List<RoomData> roomDataList = roomDataService.getRoomDataByRoom(1L);

        assertNotNull(roomDataList);
        assertEquals(2, roomDataList.size());
        verify(roomDataRepository).findByRoomId(1L);
    }
}