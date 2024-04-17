package com.example.DigitalTwin.controller;

import com.example.DigitalTwin.model.components.Door;
import com.example.DigitalTwin.service.DoorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doors")
public class DoorController {

    private final DoorService doorService;

    @Autowired
    public DoorController(DoorService doorService) {
        this.doorService = doorService;
    }

    @PostMapping
    public ResponseEntity<Door> addDoor(@RequestBody Door door) {
        Door savedDoor = doorService.saveOrUpdateDoor(door);
        return ResponseEntity.ok(savedDoor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Door> getDoorById(@PathVariable Long id) {
        Door door = doorService.findDoorById(id)
                .orElseThrow(() -> new RuntimeException("Door not found with id: " + id));
        return ResponseEntity.ok(door);
    }

    @GetMapping
    public List<Door> getAllDoors() {
        return doorService.findAllDoors();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoor(@PathVariable Long id) {
        doorService.deleteDoorById(id);
        return ResponseEntity.ok().build();
    }
}
