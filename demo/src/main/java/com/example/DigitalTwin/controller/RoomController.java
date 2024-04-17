package com.example.DigitalTwin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DigitalTwin.dto.RoomDto;
import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.service.RoomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/room")
public class RoomController {

	@Autowired
	private RoomService roomService;

	@PostMapping
	public ResponseEntity<Room> createRoom(@Valid @RequestBody RoomDto roomDetails) {
		Room createdRoom = roomService.createRoom(roomDetails);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRoom);
	}

	@PutMapping("/{roomId}")
	public ResponseEntity<Room> updateRoom(@PathVariable("roomId") Long id,@Valid @RequestBody RoomDto roomDetails) {
		Room updatedRoom = roomService.updateRoom(id, roomDetails);
		return ResponseEntity.ok(updatedRoom);
	}

	@DeleteMapping("/{roomId}")
	public ResponseEntity<String> deleteRoom(@PathVariable("roomId") Long id) {
		roomService.deleteRoom(id);
		return ResponseEntity.ok("Room deleted successfully");
	}

	@GetMapping("/{roomId}")
	public ResponseEntity<Room> getRoomById(@PathVariable("roomId") Long id) {
		Room room = roomService.getRoomById(id);
		return ResponseEntity.ok(room);
	}

	@GetMapping
	public ResponseEntity<List<Room>> getAllRooms() {
		List<Room> rooms = roomService.getAllRooms();
		return ResponseEntity.ok(rooms);
	}

	@GetMapping("/report")
	public ResponseEntity<Resource> getFile() {
		String filename = "room_report.csv";
		InputStreamResource file = new InputStreamResource(roomService.generateRoomReport());
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("text/csv")).body(file);
	}

	@GetMapping("/random/{roomId}")
	public ResponseEntity<Room> addRandomDataToRoom(@PathVariable("roomId") Long roomId) {
		Room room = roomService.addRandomDataToRoom(roomId);
		return ResponseEntity.ok(room);
	}
}
