package com.example.DigitalTwin.controller;

import com.example.DigitalTwin.model.RoomData;
import com.example.DigitalTwin.service.RoomDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(RoomDataController.class)
public class RoomDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomDataService roomDataService;

    @InjectMocks
    private RoomDataController roomDataController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(roomDataController).build();
    }

    @Test
    public void testGetRoomDataByRoom() throws Exception {
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

        List<RoomData> roomDataList = Arrays.asList(roomData1, roomData2);

        when(roomDataService.getRoomDataByRoom(1L)).thenReturn(roomDataList);

        mockMvc.perform(get("/roomData/getBy/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].co2Level").value(400.0))
                .andExpect(jsonPath("$[1].co2Level").value(450.0));
    }

}
