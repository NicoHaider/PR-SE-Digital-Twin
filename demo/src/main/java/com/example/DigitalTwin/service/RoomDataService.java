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

@Service
public class RoomDataService {
    @Autowired
    private RoomDataRepository roomDataRepository;

    @Autowired
    private RoomRepository roomRepository;

    private final Random random = new Random();

    public void generateRandomRoomData() {
        List<Room> rooms = roomRepository.findAll();

        for (Room room : rooms) {
            RoomData data = new RoomData();
            data.setRoom(room);
            data.setCo2Level(random.nextDouble() * (5000 - 300) + 300);
            data.setTemperature(random.nextDouble() * (30 - 18) + 18);
            data.setNumOfPeople(random.nextInt(21));
            data.setDateTime(LocalDateTime.now());
            roomDataRepository.save(data);
        }
    }
}
