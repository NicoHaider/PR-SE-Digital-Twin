package com.example.DigitalTwin.service;

import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
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

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public InputStreamResource generateRoomReport(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(outputStream, true, StandardCharsets.UTF_8);
        writer.println("ID;Name;Size");

        //sample data TODO: add the right data
        writer.println("1;Room A;10");
        writer.println("2;Room B;20");
        writer.println("3;Room C;30");

        // Flush and close the writer
        writer.flush();
        writer.close();

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        return new InputStreamResource(inputStream);
    }

    // Weitere Service-Methoden können hier hinzugefügt werden.
}

