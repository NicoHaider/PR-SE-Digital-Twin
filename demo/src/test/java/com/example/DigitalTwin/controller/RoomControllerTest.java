package com.example.DigitalTwin.controller;

import com.example.DigitalTwin.dto.RoomDto;
import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(RoomController.class)
public class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(roomController).build();
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCreateRoom() throws Exception {
        RoomDto roomDto = new RoomDto();
        roomDto.setName("Conference Room");
        roomDto.setSize(100.0);
        roomDto.setType("Office");

        Room room = new Room();
        room.setId(1L);
        room.setName("Conference Room");
        room.setSize(100.0);
        room.setType("Office");

        when(roomService.createRoom(any(RoomDto.class))).thenReturn(room);

        mockMvc.perform(post("/room")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(roomDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Conference Room"));
    }

    @Test
    public void testUpdateRoom() throws Exception {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(1L);
        roomDto.setName("Conference Room Updated");
        roomDto.setSize(150.0);
        roomDto.setType("Office");

        RoomDto updatedRoomDto = new RoomDto();
        updatedRoomDto.setId(1L);
        updatedRoomDto.setName("Conference Room Updated");
        updatedRoomDto.setSize(150.0);
        updatedRoomDto.setType("Office");

        when(roomService.updateRoom(any(RoomDto.class))).thenReturn(updatedRoomDto);

        mockMvc.perform(put("/room")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(roomDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Conference Room Updated"));
    }

    @Test
    public void testDeleteRoom() throws Exception {
        mockMvc.perform(delete("/room/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Room deleted successfully"));
    }

    @Test
    public void testGetRoomById() throws Exception {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(1L);
        roomDto.setName("Conference Room");
        roomDto.setSize(100.0);
        roomDto.setType("Office");

        when(roomService.getRoomById(1L)).thenReturn(roomDto);

        mockMvc.perform(get("/room/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Conference Room"));
    }

    @Test
    public void testGetAllRooms() throws Exception {
        RoomDto roomDto1 = new RoomDto();
        roomDto1.setId(1L);
        roomDto1.setName("Conference Room");
        roomDto1.setSize(100.0);
        roomDto1.setType("Office");

        RoomDto roomDto2 = new RoomDto();
        roomDto2.setId(2L);
        roomDto2.setName("Meeting Room");
        roomDto2.setSize(150.0);
        roomDto2.setType("Office");

        List<RoomDto> roomList = new ArrayList<>();
        roomList.add(roomDto1);
        roomList.add(roomDto2);

        when(roomService.getAllRooms()).thenReturn(roomList);

        mockMvc.perform(get("/room"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Conference Room"))
                .andExpect(jsonPath("$[1].name").value("Meeting Room"));
    }

    @Test
    public void testGenerateRoomReport() throws Exception {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("sample,csv,data".getBytes());
        InputStreamResource file = new InputStreamResource(byteArrayInputStream);

        when(roomService.generateRoomReport()).thenReturn(byteArrayInputStream);

        mockMvc.perform(get("/room/report"))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=room_report.csv"))
                .andExpect(content().contentType(MediaType.parseMediaType("text/csv")));
    }

    @Test
    public void testAddRandomDataToRoom() throws Exception {
        Room room = new Room();
        room.setId(1L);
        room.setName("Conference Room");

        when(roomService.addRandomDataToRoom(1L)).thenReturn(room);

        mockMvc.perform(get("/room/random/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Conference Room"));
    }
}
