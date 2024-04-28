package com.example.DigitalTwin.controller;

import com.example.DigitalTwin.model.components.Light;
import com.example.DigitalTwin.service.LightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lights")
public class LightController {

    private final LightService lightService;

    @Autowired
    public LightController(LightService lightService) {
        this.lightService = lightService;
    }

    @PostMapping
    public ResponseEntity<Light> addLight(@RequestBody Light light) {
        Light savedLight = lightService.saveOrUpdateLight(light);
        return ResponseEntity.ok(savedLight);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Light> getLightById(@PathVariable Long id) {
        Light light = lightService.findLightById(id)
                .orElseThrow(() -> new RuntimeException("Light not found with id: " + id));
        return ResponseEntity.ok(light);
    }

    @GetMapping
    public List<Light> getAllLights() {
        return lightService.findAllLights();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLight(@PathVariable Long id) {
        lightService.deleteLightById(id);
        return ResponseEntity.ok().build();
    }
}
