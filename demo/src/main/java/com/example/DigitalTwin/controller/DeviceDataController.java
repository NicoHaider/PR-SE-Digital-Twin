package com.example.DigitalTwin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DigitalTwin.model.DeviceData;
import com.example.DigitalTwin.service.DeviceDataService;

@RestController
@RequestMapping("deviceData")
public class DeviceDataController {

    @Autowired
    private DeviceDataService deviceDataService;

    @GetMapping("getAllBy/{id}")
    public List<DeviceData> getDeviceDataByRoom(@PathVariable long id) {
        return deviceDataService.getRoomDataByRoom(id);
    }
}
