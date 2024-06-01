package com.example.DigitalTwin.service;

import com.example.DigitalTwin.dto.DeviceDto;
import com.example.DigitalTwin.dto.DoorDto;
import com.example.DigitalTwin.dto.RoomDto;
import com.example.DigitalTwin.exception.AppException;
import com.example.DigitalTwin.exception.NotFoundException;
import com.example.DigitalTwin.model.Device;
import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.model.RoomData;
import com.example.DigitalTwin.repository.DeviceRepository;
import com.example.DigitalTwin.repository.RoomDataRepository;
import com.example.DigitalTwin.repository.RoomRepository;
import com.example.DigitalTwin.utils.CSVUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoomService {

	@Autowired
	RoomRepository roomRepo;

	@Autowired
	RoomDataRepository roomDataRepo;

	@Autowired
	DeviceRepository deviceRepository;

	@Transactional
	public Room createRoom(RoomDto roomDetails) {
		try {
			Room room = new Room();
			room.setName(roomDetails.getName());
			room.setSize(roomDetails.getSize());
			room.setType(roomDetails.getType());
			room =  roomRepo.save(room);

			List<DeviceDto> deviceDtoList = roomDetails.getDeviceDtoList();
			if (!deviceDtoList.isEmpty()) {
				List<Device> deviceList = new ArrayList<>();
				for (DeviceDto deviceDto : deviceDtoList) {
					Device device = new Device();
					device.setName(deviceDto.getName());
					device.setRoom(room);
					device.setStatus(deviceDto.getStatus());
					device.setDeviceType(deviceDto.getDeviceType());
					device.setTime(new Date());

					deviceList.add(device);
				}
				deviceRepository.saveAll(deviceList);
			}


			return room;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
	}

	public RoomDto updateRoom(RoomDto roomDetails) {
		Optional<Room> optionalRoom = roomRepo.findById(roomDetails.getId());
		if (optionalRoom.isPresent()) {
			Room room = optionalRoom.get();
			room.setName(roomDetails.getName());
			room.setSize(roomDetails.getSize());
			room.setType(roomDetails.getType());
			return roomRepo.save(room).getDto();
		} else {
			throw new EntityNotFoundException("Room not found");
		}
	}

	@Transactional
	public Room updateRoom(Long id, RoomDto roomDetails) {
		try {
			Room room = roomRepo.findById(id).orElseThrow(() -> new NotFoundException("Room id not found"));

			if (roomDetails.getName() != null && !roomDetails.getName().isEmpty()) {
				room.setName(roomDetails.getName());
			}

			if (roomDetails.getSize() >= 0) {
				room.setSize(roomDetails.getSize());
			}

//			if (roomDetails.getDoors() >= 0) {
//				room.setDoors(roomDetails.getDoors());
//			}

//			if (roomDetails.getWindows() >= 0) {
//				room.setWindows(roomDetails.getWindows());
//			}
//
//			if (roomDetails.getLights() >= 0) {
//				room.setLights(roomDetails.getLights());
//			}

//			if (roomDetails.getFans() >= 0) {
//				room.setFans(roomDetails.getFans());
//			}
			return roomRepo.save(room);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
	}

	@Transactional
	public String deleteRoom(Long id) {
		try {
			Room room = roomRepo.findById(id).orElseThrow(() -> new NotFoundException("Room id not found"));
			deviceRepository.deleteAllByRoomId(id);
			roomRepo.delete(room);
			return "Room deleted successfully";
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public RoomDto getRoomById(Long id) {
		try {
			Room room = roomRepo.findById(id).orElseThrow(() -> new NotFoundException("Room id not found"));
			RoomDto roomDto = room.getDto();
			roomDto.setDeviceDtoList(room.getDevices().stream().map(Device::getDto).collect(Collectors.toList()));

			return roomDto;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public List<RoomDto> getAllRooms() {
		try {
			return roomRepo.findAll().stream().map(Room::getDto).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
	}

	@Transactional
	public Room addRandomDataToRoom(Long roomId) {
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

		return room;
	}

	private int generateRandomValue(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}

	public ByteArrayInputStream  generateRoomReport() {
		try {
			List<Room> rooms = roomRepo.findAll();
			ByteArrayInputStream in = CSVUtil.roomsToCSV(rooms);
			return in;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
	}

	public Room saveOrUpdateRoom(Room room) {
		return room;
	}
}
