package com.example.DigitalTwin.controller;

import com.example.DigitalTwin.model.components.Fan;
import com.example.DigitalTwin.service.FanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fans")
public class FanController {

    private final FanService fanService;

    @Autowired
    public FanController(FanService fanService) {
        this.fanService = fanService;
    }

    @PostMapping
    public ResponseEntity<Fan> addFan(@RequestBody Fan fan) {
        Fan savedFan = fanService.saveOrUpdateFan(fan);
        return ResponseEntity.ok(savedFan);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fan> getFanById(@PathVariable Long id) {
        Fan fan = fanService.findFanById(id)
                .orElseThrow(() -> new RuntimeException("Fan not found with id: " + id));
        return ResponseEntity.ok(fan);
    }

    @GetMapping
    public List<Fan> getAllFans() {
        return fanService.findAllFans();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFan(@PathVariable Long id) {
        fanService.deleteFanById(id);
        return ResponseEntity.ok().build();
    }
}
