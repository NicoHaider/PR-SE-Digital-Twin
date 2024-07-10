package com.example.DigitalTwin.controller;

import com.example.DigitalTwin.dto.DeviceDto;
import com.example.DigitalTwin.enums.DeviceType;
import com.example.DigitalTwin.service.DeviceService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
@ExtendWith(MockitoExtension.class)
@WebMvcTest(DeviceController.class)
public class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeviceService deviceService;

    @InjectMocks
    private DeviceController deviceController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(deviceController).build();
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCreateDevice() throws Exception {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setName("Thermostat");
        deviceDto.setStatus(true);
        deviceDto.setDeviceType(DeviceType.Light);
        deviceDto.setRoomId(1L);

        when(deviceService.createDevice(any(DeviceDto.class))).thenReturn(deviceDto);

        mockMvc.perform(post("/device")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(deviceDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Thermostat"));
    }

    @Test
    public void testCreateDeviceWithException() throws Exception {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setName("Thermostat");
        deviceDto.setStatus(true);
        deviceDto.setDeviceType(DeviceType.Light);
        deviceDto.setRoomId(1L);

        when(deviceService.createDevice(any(DeviceDto.class))).thenThrow(new EntityNotFoundException("Room Not present"));

        mockMvc.perform(post("/device")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(deviceDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Room Not present"));
    }

    @Test
    public void testUpdateDeviceStatus() throws Exception {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setId(1L);
        deviceDto.setStatus(false);

        when(deviceService.updateDeviceStatus(1L)).thenReturn(deviceDto);

        mockMvc.perform(get("/device/1"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(false));
    }

    @Test
    public void testUpdateDeviceStatusWithException() throws Exception {
        when(deviceService.updateDeviceStatus(1L)).thenThrow(new EntityNotFoundException("Device Not present"));

        mockMvc.perform(get("/device/1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Device Not present"));
    }

    @Test
    public void testDeleteDevice() throws Exception {
        when(deviceService.deleteDevice(1L)).thenReturn(true);

        mockMvc.perform(get("/device/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testDeleteDeviceWithException() throws Exception {
        when(deviceService.deleteDevice(1L)).thenThrow(new EntityNotFoundException("Device Not present"));

        mockMvc.perform(get("/device/delete/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").value("Device Not present"));
    }

    @Test
    public void testGetDevice() throws Exception {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setId(1L);
        deviceDto.setName("Thermostat");
        deviceDto.setDeviceType(DeviceType.Light);

        when(deviceService.getDevice(1L)).thenReturn(deviceDto);

        mockMvc.perform(get("/device/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Thermostat"));
    }

    @Test
    public void testGetDeviceWithException() throws Exception {
        when(deviceService.getDevice(1L)).thenThrow(new EntityNotFoundException("Device Not present"));

        mockMvc.perform(get("/device/get/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").value("Device Not present"));
    }

    @Test
    public void testUpdateDevice() throws Exception {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setName("Thermostat Updated");
        deviceDto.setStatus(true);
        deviceDto.setDeviceType(DeviceType.Light);
        deviceDto.setRoomId(1L);

        when(deviceService.updateDevice(any(DeviceDto.class))).thenReturn(deviceDto);

        mockMvc.perform(post("/device/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(deviceDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Thermostat Updated"));
    }

    @Test
    public void testUpdateDeviceWithException() throws Exception {
        DeviceDto deviceDto = new DeviceDto();
        deviceDto.setName("Thermostat Updated");
        deviceDto.setStatus(true);
        deviceDto.setDeviceType(DeviceType.Light);
        deviceDto.setRoomId(1L);

        when(deviceService.updateDevice(any(DeviceDto.class))).thenThrow(new EntityNotFoundException("Device Not present"));

        mockMvc.perform(post("/device/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(deviceDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Device Not present"));
    }
}
