package com.example.DigitalTwin.model;

import com.example.DigitalTwin.dto.RoomDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private double size;
	private int doors;
	private int windows;
	private int lights;
	private int fans;
	private double temperature; // Added
	private double co2; // Added
	private int peopleCount; // Added

	private String type; // Added

	@OneToMany(mappedBy = "room")
	private List<Device> devices = new ArrayList<>(); // Added

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	@JsonProperty(value = "climate_data")
	private List<RoomData> roomDataList;

	@OneToMany(mappedBy = "room")
	private List<AutomationRule> automationRules = new ArrayList<>();

	// Konstruktoren
	public Room(String name, double size, int doors, int windows, int lights, int fans, double temperature, double co2, int peopleCount) {
		this.name = name;
		this.size = size;
		this.doors = doors;
		this.windows = windows;
		this.lights = lights;
		this.fans = fans;
		this.temperature = temperature; // Added
		this.co2 = co2; // Added
		this.peopleCount = peopleCount; // Added
	}

	public Room() {
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public int getDoors() {
		return doors;
	}

	public void setDoors(int doors) {
		this.doors = doors;
	}

	public int getWindows() {
		return windows;
	}

	public void setWindows(int windows) {
		this.windows = windows;
	}

	public int getLights() {
		return lights;
	}

	public void setLights(int lights) {
		this.lights = lights;
	}

	public int getFans() {
		return fans;
	}

	public void setFans(int fans) {
		this.fans = fans;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getCo2() {
		return co2;
	}

	public void setCo2(double co2) {
		this.co2 = co2;
	}

	public int getPeopleCount() {
		return peopleCount;
	}

	public void setPeopleCount(int peopleCount) {
		this.peopleCount = peopleCount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<RoomData> getRoomDataList() {
		return roomDataList;
	}
	public void setRoomDataList(List<RoomData> roomDataList) {
		this.roomDataList = roomDataList;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	// Added
	public RoomDto getDto() {
		RoomDto dto = new RoomDto();
		dto.setId(this.id);
		dto.setName(this.name);
		dto.setSize(this.size);
		dto.setType(this.type);
		dto.setDeviceDtoList(this.devices.stream().map(Device::getDto).collect(Collectors.toList()));
		dto.setAutomationRuleDtoList(this.automationRules.stream().map(AutomationRule::getDto).collect(Collectors.toList()));
		return dto;
	}
}
