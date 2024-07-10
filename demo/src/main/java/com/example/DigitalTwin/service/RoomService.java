package com.example.DigitalTwin.service;

import com.example.DigitalTwin.dto.DeviceDto;
import com.example.DigitalTwin.dto.RoomDto;
import com.example.DigitalTwin.enums.DeviceType;
import com.example.DigitalTwin.exception.AppException;
import com.example.DigitalTwin.exception.NotFoundException;
import com.example.DigitalTwin.model.Device;
import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.model.RoomData;
import com.example.DigitalTwin.repository.DeviceDataRepository;
import com.example.DigitalTwin.repository.DeviceRepository;
import com.example.DigitalTwin.repository.RoomDataRepository;
import com.example.DigitalTwin.repository.RoomRepository;
import com.example.DigitalTwin.utils.CSVUtil;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomService {

	private static final Logger logger = LogManager.getLogger(RoomService.class);

	@Autowired
	RoomRepository roomRepo;

	@Autowired
	RoomDataRepository roomDataRepo;

	@Autowired
	DeviceRepository deviceRepository;

	@Autowired
	RoomDataRepository roomDataRepository;

	@Autowired
	DeviceDataRepository deviceDataRepository;

	@Autowired
	DeviceDataService deviceDataService;

	@Autowired
	private AutomationRuleService automationRuleService;

	@Transactional
	public Room createRoom(RoomDto roomDetails) {
		logger.info("Request to create room: " + roomDetails);
		try {
			Room room = new Room();
			room.setName(roomDetails.getName());
			room.setSize(roomDetails.getSize());
			room.setType(roomDetails.getType());
			room =  roomRepo.save(room);

			List<DeviceDto> deviceDtoList = roomDetails.getDeviceDtoList();
			if (!deviceDtoList.isEmpty()) {
				List<Device> deviceList = new ArrayList<>();
				int numWindows = 0;
				int numDoors = 0;
				int numLights = 0;
				int numFans = 0;
				for (DeviceDto deviceDto : deviceDtoList) {
					Device device = new Device();
					device.setName(deviceDto.getName());
					device.setRoom(room);
					device.setStatus(deviceDto.getStatus());
					DeviceType type = deviceDto.getDeviceType();
					if (type.equals(DeviceType.Window)) {numWindows++;
					} else if (type.equals(DeviceType.Door)) {numDoors++;
					} else if (type.equals(DeviceType.Light)) {numLights++;
					} else if (type.equals(DeviceType.Fan)) {numFans++;}
					device.setDeviceType(type);
					device.setTime(new Date());

					deviceList.add(device);
				}
				List<Device> savedDevices = deviceRepository.saveAll(deviceList);
				room.setWindows(numWindows);
				room.setDoors(numDoors);
				room.setLights(numLights);
				room.setFans(numFans);
					
				if(!savedDevices.isEmpty()){
					deviceDataService.addDeviceData(room);
				}
			automationRuleService.createDefaultRules(room);
			}
			logger.info("Room created successfully: " + room);
			return room;
		} catch (Exception e) {
			logger.error("Error creating room: " + e.getMessage(), e);
			throw new AppException(e.getMessage());
		}
	}

	public RoomDto updateRoom(RoomDto roomDetails) {
		logger.info("Request to update room: " + roomDetails);
		Optional<Room> optionalRoom = roomRepo.findById(roomDetails.getId());
		if (optionalRoom.isPresent()) {
			Room room = optionalRoom.get();
			room.setName(roomDetails.getName());
			room.setSize(roomDetails.getSize());
			room.setType(roomDetails.getType());
			RoomDto updatedRoom = roomRepo.save(room).getDto();
			logger.info("Room updated successfully: " + updatedRoom);
			return updatedRoom;
		} else {
			logger.error("Room with id: "+ roomDetails.getId() + " not found.");
			throw new EntityNotFoundException("Room not found");
		}
	}

	@Transactional
	public Room updateRoom(Long id, RoomDto roomDetails) {
		logger.info("Request to update room with id: " + id + " with details: " + roomDetails);
		try {
			Room room = roomRepo.findById(id).orElseThrow(() -> new NotFoundException("Room id not found"));

			if (roomDetails.getName() != null && !roomDetails.getName().isEmpty()) {
				room.setName(roomDetails.getName());
			}

			if (roomDetails.getSize() >= 0) {
				room.setSize(roomDetails.getSize());
			}
			Room updatedRoom = roomRepo.save(room);
			logger.info("Room updated successfully: " + updatedRoom);
			return updatedRoom;
		} catch (Exception e) {
			logger.error("Error updating room: " + e.getMessage(), e);
			throw new AppException(e.getMessage());
		}
	}

	@Transactional
	public String deleteRoom(Long id) {
		logger.info("Request to delete room with id: " + id);
		try {
			Room room = roomRepo.findById(id).orElseThrow(() -> new NotFoundException("Room id not found"));
			deviceDataRepository.deleteByRoomId(id);
			deviceRepository.deleteAllByRoomId(id);
			roomDataRepository.deleteByRoomId(id);
			roomRepo.delete(room);
			logger.info("Room deleted successfully with id: " + id);
			return "Room deleted successfully";
		} catch (NotFoundException e) {
			logger.error("Room not found with id: " + id, e);
			throw e; // Re-throw NotFoundException to be caught by the test
		} catch (Exception e) {
			logger.error("Error deleting room: " + e.getMessage(), e);
			throw new AppException(e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public RoomDto getRoomById(Long id) {
		logger.info("Request to get room by id: " + id);
		try {
			Room room = roomRepo.findById(id).orElseThrow(() -> new NotFoundException("Room id not found"));
			RoomDto roomDto = room.getDto();
			roomDto.setDeviceDtoList(room.getDevices().stream().map(Device::getDto).collect(Collectors.toList()));
			logger.info("Room retrieved successfully: " + roomDto);
			return roomDto;
		} catch (NotFoundException e) {
			logger.error("Room not found with id: " + id, e);
			throw e;
		} catch (Exception e) {
			logger.error("Error retrieving room: " + e.getMessage(), e);
			throw new AppException(e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public List<RoomDto> getAllRooms() {
		logger.info("Request to get all rooms");
		try {
			List<RoomDto> rooms = roomRepo.findAll().stream().map(Room::getDto).collect(Collectors.toList());
			logger.info("Rooms retrieved successfully");
			return rooms;
		} catch (Exception e) {
			logger.error("Error retrieving rooms: " + e.getMessage(), e);
			throw new AppException(e.getMessage());
		}
	}

	@Transactional
	public Room addRandomDataToRoom(Long roomId) {
		logger.info("Request to add random data to room with id: " + roomId);
		try {
			Room room = roomRepo.findById(roomId).orElseThrow(() -> new NotFoundException("Room not found"));

			List<RoomData> roomDatas = new ArrayList<>();

			Random random = new Random();
			int co2Min = 300;
			int co2Max = 1000;
			double tempMin = 10.0;
			double tempMax = 40.0;
			LocalDateTime startTime = LocalDateTime.now().minusHours(24);
			for (int hour = 0; hour < 24; hour++) {
				RoomData roomData = new RoomData();
				int numOfPeople = generateRandomValue(0, 10);
				roomData.setNumOfPeople(numOfPeople);
				roomData.setRoom(room);
				int co2 = random.nextInt(co2Max - co2Min + 1) + co2Min;
				roomData.setCo2Level(co2);
				double temperature = tempMin + (tempMax - tempMin) * random.nextDouble();
				roomData.setTemperature(temperature);
				LocalDateTime currentTime = startTime.plusHours(hour);
				roomData.setDateTime(currentTime);
				RoomData save = roomDataRepo.save(roomData);
				roomDatas.add(save);
			}
			logger.info("Random data added successfully to room with id: " + roomId);
			return room;
		} catch (Exception e) {
			logger.error("Error adding random data to room: " + e.getMessage(), e);
			throw new AppException(e.getMessage());
		}
	}

	private int generateRandomValue(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}

	public ByteArrayInputStream  generateRoomReport() {
		logger.info("Request to generate room report");
		try {
			List<Room> rooms = roomRepo.findAll();
			ByteArrayInputStream in = CSVUtil.roomsToCSV(rooms);
			logger.info("Room report generated successfully");
			return in;
		} catch (Exception e) {
			logger.error("Error generating room report " + e.getMessage(), e);
			throw new AppException(e.getMessage());
		}
	}

	public Room saveOrUpdateRoom(Room room) {
		return room;
	}

}