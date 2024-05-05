package com.example.DigitalTwin.controller;

import com.example.DigitalTwin.model.components.Window;
import com.example.DigitalTwin.service.WindowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/windows")
public class WindowController {

    private final WindowService windowService;

    @Autowired
    public WindowController(WindowService windowService) {
        this.windowService = windowService;
    }

    @PostMapping
    public ResponseEntity<Window> addWindow(@RequestBody Window window) {
        Window savedWindow = windowService.saveOrUpdateWindow(window);
        return ResponseEntity.ok(savedWindow);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Window> getWindowById(@PathVariable Long id) {
        Window window = windowService.findWindowById(id)
                .orElseThrow(() -> new RuntimeException("Window not found with id: " + id));
        return ResponseEntity.ok(window);
    }

    @GetMapping
    public List<Window> getAllWindows() {
        return windowService.findAllWindows();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWindow(@PathVariable Long id) {
        windowService.deleteWindowById(id);
        return ResponseEntity.ok().build();
    }
}
