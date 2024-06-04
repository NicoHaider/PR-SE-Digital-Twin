package com.example.DigitalTwin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DigitalTwin.model.RoomData;
import com.example.DigitalTwin.service.RoomDataService;
import com.example.DigitalTwin.utils.ScheduledTasks;
import java.util.List;

@RestController
@RequestMapping("roomData")
public class RoomDataController {

    @Autowired
    private RoomDataService roomDataService;

    @GetMapping("getAllBy/{id}")
    public List<RoomData> getRoomDataByRoom(@PathVariable long id) {
        return roomDataService.getRoomDataByRoom(id);
    }

    @GetMapping("getBy/{id}")
    public RoomData getLastRoomDataByRoom(@PathVariable long id) {
        List<RoomData> roomDataList = roomDataService.getRoomDataByRoom(id);
        RoomData lastRoomData = null;
        if (!roomDataList.isEmpty()) {
            lastRoomData = roomDataList.get(roomDataList.size() - 1);
        }
        return lastRoomData;
    }
}
