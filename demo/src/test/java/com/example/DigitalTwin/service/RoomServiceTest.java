package com.example.DigitalTwin.service;

import com.example.DigitalTwin.dto.DeviceDto;
import com.example.DigitalTwin.dto.RoomDto;
import com.example.DigitalTwin.enums.DeviceType;
import com.example.DigitalTwin.exception.AppException;
import com.example.DigitalTwin.exception.NotFoundException;
import com.example.DigitalTwin.model.Device;
import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.model.RoomData;
import com.example.DigitalTwin.repository.DeviceRepository;
import com.example.DigitalTwin.repository.RoomDataRepository;
import com.example.DigitalTwin.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {
/*
    @Mock
    private RoomRepository roomRepository;

    @Mock
    private RoomDataRepository roomDataRepo;

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private RoomService roomService;

    @Test
    public void testGetRoomById() {
        Room room = new Room();
        room.setId(1L);
        room.setName("Conference Room");
        room.setSize(100.0);
        room.setDevices(new ArrayList<>());

        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        RoomDto foundRoom = roomService.getRoomById(1L);

        assertNotNull(foundRoom);
        assertEquals("Conference Room", foundRoom.getName());
        verify(roomRepository).findById(1L);
    }

    @Test
    public void testGetRoomById_NotFound() {
        when(roomRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> roomService.getRoomById(1L));
        assertEquals("Room id not found", exception.getMessage());
    }

    @Test
    public void testCreateRoom() {
        RoomDto roomDto = new RoomDto();
        roomDto.setName("Conference Room");
        roomDto.setSize(100.0);
        roomDto.setType("Office");

        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setName("Thermostat");
        deviceDto.setStatus(true);
        deviceDto.setDeviceType(DeviceType.Light);
        List<DeviceDto> deviceDtoList = new ArrayList<>();
        deviceDtoList.add(deviceDto);
        roomDto.setDeviceDtoList(deviceDtoList);

        Room room = new Room();
        room.setId(1L);
        room.setName("Conference Room");
        room.setSize(100.0);
        room.setType("Office");

        when(roomRepository.save(any(Room.class))).thenReturn(room);
        when(deviceRepository.saveAll(anyList())).thenReturn(new ArrayList<>());

        Room createdRoom = roomService.createRoom(roomDto);

        assertNotNull(createdRoom);
        assertEquals("Conference Room", createdRoom.getName());
        verify(roomRepository).save(any(Room.class));
        verify(deviceRepository).saveAll(anyList());
    }

    @Test
    public void testUpdateRoom() {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(1L);
        roomDto.setName("Conference Room Updated");
        roomDto.setSize(150.0);
        roomDto.setType("Office");

        Room room = new Room();
        room.setId(1L);
        room.setName("Conference Room");
        room.setSize(100.0);
        room.setType("Office");

        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        when(roomRepository.save(any(Room.class))).thenReturn(room);

        RoomDto updatedRoom = roomService.updateRoom(roomDto);

        assertNotNull(updatedRoom);
        assertEquals("Conference Room Updated", updatedRoom.getName());
        assertEquals(150.0, updatedRoom.getSize());
        verify(roomRepository).findById(1L);
        verify(roomRepository).save(any(Room.class));
    }

    @Test
    public void testUpdateRoom_NotFound() {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(1L);
        roomDto.setName("Conference Room Updated");

        when(roomRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> roomService.updateRoom(roomDto));
        assertEquals("Room not found", exception.getMessage());
    }

    @Test
    public void testDeleteRoom() {
        Room room = new Room();
        room.setId(1L);

        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));

        String result = roomService.deleteRoom(1L);

        assertEquals("Room deleted successfully", result);
        verify(deviceRepository).deleteAllByRoomId(1L);
        verify(roomRepository).delete(any(Room.class));
    }

    @Test
    public void testDeleteRoom_NotFound() {
        when(roomRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> roomService.deleteRoom(1L));
        assertEquals("Room id not found", exception.getMessage());
    }

    @Test
    public void testGetAllRooms() {
        Room room1 = new Room();
        room1.setId(1L);
        room1.setName("Conference Room");

        Room room2 = new Room();
        room2.setId(2L);
        room2.setName("Meeting Room");

        List<Room> rooms = new ArrayList<>();
        rooms.add(room1);
        rooms.add(room2);

        when(roomRepository.findAll()).thenReturn(rooms);

        List<RoomDto> allRooms = roomService.getAllRooms();

        assertNotNull(allRooms);
        assertEquals(2, allRooms.size());
        verify(roomRepository).findAll();
    }

    @Test
    public void testAddRandomDataToRoom() {
        Room room = new Room();
        room.setId(1L);

        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        when(roomDataRepo.save(any(RoomData.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Room updatedRoom = roomService.addRandomDataToRoom(1L);

        assertNotNull(updatedRoom);
        verify(roomRepository).findById(1L);
        verify(roomDataRepo, times(24)).save(any(RoomData.class));
    }

    @Test
    public void testAddRandomDataToRoom_NotFound() {
        when(roomRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> roomService.addRandomDataToRoom(1L));
        assertEquals("Room not found", exception.getMessage());
    }*/
}