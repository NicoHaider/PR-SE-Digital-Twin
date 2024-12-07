package com.example.DigitalTwin.controller;


import com.example.DigitalTwin.dto.DeviceDto;
import com.example.DigitalTwin.service.DeviceService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@RestController
@RequestMapping("/device")
@CrossOrigin
public class DeviceController {

    private static final Logger logger = LogManager.getLogger(DeviceController.class);

    @Autowired
    private DeviceService deviceService;

    @PostMapping
    public ResponseEntity<?> createDevice(@Valid @RequestBody DeviceDto deviceDto) {
        logger.info("Entering createDevice method");
        try{
            logger.info("Creating device: {}", deviceDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(deviceService.createDevice(deviceDto));
        } catch (EntityNotFoundException e){
            logger.error("Entity not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e){
            logger.error("An error occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong.");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> updateDeviceStatus(@PathVariable("id") Long id) {
        logger.info("Entering updateDeviceStatus method with ID: {}", id);
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(deviceService.updateDeviceStatus(id));
        } catch (EntityNotFoundException e){
            logger.error("Entity not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e){
            logger.error("An error occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong.");
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteDevice (@PathVariable("id") Long id) {
        logger.info("Entering deleteDevice method with ID: {}", id);
        try{
            return ResponseEntity.status(HttpStatus.OK).body(deviceService.deleteDevice(id));
        } catch (EntityNotFoundException e){
            logger.error("Entity not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getDevice (@PathVariable("id") Long id) {
        logger.info("Entering getDevice method with ID: {}", id);
        try{
            return ResponseEntity.status(HttpStatus.OK).body(deviceService.getDevice(id));
        } catch (EntityNotFoundException e){
            logger.error("Entity not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateDevice(@Valid @RequestBody DeviceDto deviceDto) {
        logger.info("Entering updateDevice method");
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(deviceService.updateDevice(deviceDto));
        } catch (EntityNotFoundException e){
            logger.error("Entity not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e){
            logger.error("An error occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong.");
        }

    }
}
