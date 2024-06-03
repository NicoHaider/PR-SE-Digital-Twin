package com.example.DigitalTwin.controller;

import com.example.DigitalTwin.model.RoomData;
import com.example.DigitalTwin.service.RoomDataService;
import com.example.DigitalTwin.utils.ScheduledTasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("roomData")
public class RoomDataController {

    @Autowired
    private RoomDataService roomDataService;

    @GetMapping("/{id}")
    public List<RoomData> getRoomDataByRoom(@PathVariable long id) {
        return roomDataService.getRoomDataByRoom(id);
    }
}
