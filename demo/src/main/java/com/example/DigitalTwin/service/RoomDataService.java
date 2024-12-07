package com.example.DigitalTwin.service;

import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.model.RoomData;
import com.example.DigitalTwin.repository.RoomRepository;
import com.example.DigitalTwin.repository.RoomDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Service
public class RoomDataService {
    private static final Logger logger = LogManager.getLogger(RoomDataService.class);
    @Autowired
    private RoomDataRepository roomDataRepository;

    @Autowired
    private RoomRepository roomRepository;

    private final Random random = new Random();

    public void generateRandomRoomData() {
        logger.info("Request to generate random room data");
        try {
            List<Room> rooms = roomRepository.findAll();

            for (Room room : rooms) {
                RoomData data = new RoomData();
                data.setRoom(room);
                data.setCo2Level((int) (random.nextDouble() * (5000 - 300) + 300));
                data.setTemperature((int) (random.nextDouble() * (30 - 18) + 18));
                data.setNumOfPeople(random.nextInt(21));
                data.setDateTime(LocalDateTime.now());
                roomDataRepository.save(data);
            }
            logger.info("Random room data generated successfully for all rooms");
        } catch (Exception e) {
            logger.error("Error with generating random room data", e);
            throw e;
        }
    }

    public List<RoomData> getRoomDataByRoom(Long roomId) {
        logger.info("Request to get room data for room id: " + roomId);
        try {
            List<RoomData> roomDataList = roomDataRepository.findByRoomId(roomId);
            logger.info("Room data retrieved successfully for room id: " + roomId);
            return roomDataList;
        } catch (Exception e) {
            logger.error("Error retrieving room data for room id: " + roomId, e);
            throw e;
        }

    }
}
