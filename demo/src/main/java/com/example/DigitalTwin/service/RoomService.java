package com.example.DigitalTwin.service;

import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room saveOrUpdateRoom(Room room) {
        return roomRepository.save(room);
    }



    public Room createOrUpdateRoom(Room room) {
        // Hier könnten weitere Geschäftslogiken implementiert werden.
        return roomRepository.save(room);
    }

    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    // Weitere Service-Methoden können hier hinzugefügt werden.
}

