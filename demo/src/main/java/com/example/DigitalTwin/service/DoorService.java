package com.example.DigitalTwin.service;

import com.example.DigitalTwin.model.components.Door;
import com.example.DigitalTwin.repository.DoorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoorService {

    private final DoorRepository doorRepository;

    @Autowired
    public DoorService(DoorRepository doorRepository) {
        this.doorRepository = doorRepository;
    }

    public Door saveOrUpdateDoor(Door door) {
        return doorRepository.save(door);
    }

    public Optional<Door> findDoorById(Long id) {
        return doorRepository.findById(id);
    }

    public List<Door> findAllDoors() {
        return doorRepository.findAll();
    }

    public void deleteDoorById(Long id) {
        doorRepository.deleteById(id);
    }
}
