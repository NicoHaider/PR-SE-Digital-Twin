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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@RestController
@RequestMapping("roomData")
public class RoomDataController {

    private static final Logger logger = LogManager.getLogger(RoomDataController.class);

    @Autowired
    private RoomDataService roomDataService;

    @GetMapping("getBy/{id}")
    public List<RoomData> getRoomDataByRoom(@PathVariable long id) {
        logger.info("Entering getRoomDataByRoom method with ID: {}", id);
        return roomDataService.getRoomDataByRoom(id);
    }
}
