package com.example.DigitalTwin.controller;

import java.util.List;

import com.example.DigitalTwin.dto.DeviceDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.example.DigitalTwin.dto.RoomDto;
import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.service.RoomService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/room")
@CrossOrigin
public class RoomController {

    private static final Logger logger = LogManager.getLogger(RoomController.class);

    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<Room> createRoom(@Valid @RequestBody RoomDto roomDetails) {
        logger.info("entering createRoom");
        Room createdRoom = roomService.createRoom(roomDetails);
        logger.info("Room created successfully: " + createdRoom);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoom);
    }

    //	@PutMapping("/{roomId}")
//	public ResponseEntity<Room> updateRoom(@PathVariable("roomId") Long id,@Valid @RequestBody RoomDto roomDetails) {
//		Room updatedRoom = roomService.updateRoom(id, roomDetails);
//		return ResponseEntity.ok(updatedRoom);
//	}
    @PutMapping
    public ResponseEntity<?> updateRoom(@Valid @RequestBody RoomDto roomDto) {
        logger.info("Received request to update room: " + roomDto);
        try {
            RoomDto updatedRoom = roomService.updateRoom(roomDto);
            logger.info("Room updated successfully: " + updatedRoom);
            return ResponseEntity.status(HttpStatus.CREATED).body(roomService.updateRoom(roomDto));
        } catch (EntityNotFoundException e) {
            logger.error("Entity not found: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Error updating room: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong.");
        }

    }

//	@PutMapping
//	public ResponseEntity<Room> updateRoom(@Valid @RequestBody RoomDto roomDetails) {
//		Room createdRoom = roomService.createRoom(roomDetails);
//		return ResponseEntity.status(HttpStatus.CREATED).body(createdRoom);
//	}

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("id") Long id) {
        logger.info("Received request to delete room with id: "+ id);
        try {
            roomService.deleteRoom(id);
            logger.info("Room deleted successfully with id: "+id);
            return ResponseEntity.ok("Room deleted successfully");
        } catch (Exception e) {
            logger.error("Error, couldn't delete room with id: " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting room");
        }
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable("roomId") Long id) {
        logger.info("Received request to get room by id: "+ id);
        try {
            RoomDto room = roomService.getRoomById(id);
            logger.info("Room retrieved successfully: " + room);
            return ResponseEntity.ok(room);
        } catch (Exception e) {
            logger.error("Error retrieving room with id: " + id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRooms() {
        logger.info("Request to get all rooms");
        try {
            List<RoomDto> rooms = roomService.getAllRooms();
            logger.info("Rooms retrieved successfully: " + rooms);
            return ResponseEntity.ok(rooms);
        } catch (Exception e) {
            logger.error("Error retrieving rooms", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/report")
    public ResponseEntity<Resource> getFile() {
        logger.info("Request to generate room report");
        try {
            String filename = "room_report.csv";
            InputStreamResource file = new InputStreamResource(roomService.generateRoomReport());
            logger.info("Room report generated successfully");
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                    .contentType(MediaType.parseMediaType("text/csv")).body(file);
        } catch (Exception e) {
            logger.error("Error generating room report", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/random/{roomId}")
    public ResponseEntity<Room> addRandomDataToRoom(@PathVariable("roomId") Long roomId) {
        logger.info("Request to add random data to room with id: "+ roomId);
        try {
            Room room = roomService.addRandomDataToRoom(roomId);
            logger.info("Random data added to room successfully: " + room);
            return ResponseEntity.ok(room);
        } catch (Exception e) {
            logger.error("Error adding random data to room with id: "+ roomId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}