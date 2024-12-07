package com.example.DigitalTwin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.example.DigitalTwin.model.RoomData;
import com.example.DigitalTwin.service.RoomDataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("roomData")
public class RoomDataController {

    private static final Logger logger = LogManager.getLogger(RoomDataController.class);

    @Autowired
    private RoomDataService roomDataService;

    @GetMapping("getAllBy/{id}")
    public List<RoomData> getRoomDataByRoom(@PathVariable long id) {
        logger.info("Entering getRoomDataByRoom method with ID: {}", id);
        return roomDataService.getRoomDataByRoom(id);
    }

    @GetMapping("getBy/{id}")
    public RoomData getLastRoomDataByRoom(@PathVariable long id) {
        logger.info("Request to get last room data for room id: " +id);
        try {
            List<RoomData> roomDataList = roomDataService.getRoomDataByRoom(id);
            RoomData lastRoomData = null;
            if (!roomDataList.isEmpty()) {
                lastRoomData = roomDataList.get(roomDataList.size() - 1);
            }
            logger.info("Last room data for room id " + id + ": " + lastRoomData);
            return lastRoomData;
        } catch (Exception e) {
            logger.error("Error retrieving last room data for room id: "+id, e);
            return null;
        }
    }
}
