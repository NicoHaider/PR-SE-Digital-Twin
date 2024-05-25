package com.example.DigitalTwin.controller;


import com.example.DigitalTwin.dto.DeviceDto;
import com.example.DigitalTwin.dto.RoomDto;
import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.service.DeviceService;
import com.example.DigitalTwin.service.RoomService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device")
@CrossOrigin
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping
    public ResponseEntity<?> createDevice(@Valid @RequestBody DeviceDto deviceDto) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(deviceService.createDevice(deviceDto));
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong.");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> updateDeviceStatus(@PathVariable("id") Long id) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(deviceService.updateDeviceStatus(id));
        } catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong.");
        }
    }
}
