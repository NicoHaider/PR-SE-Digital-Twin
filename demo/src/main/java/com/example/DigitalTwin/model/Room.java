package com.example.DigitalTwin.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "room_seq_generator", sequenceName = "room_seq", allocationSize = 1)
	private Long id;

	private String name;

	private double size;

	private int doors;

	private int windows;

	private int lights;
	private int fans;

	@OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
	@JsonProperty(value = "climate_data")
	private List<RoomData> roomDataList;

	public Room() {
		super();
	}

	public Room(String name, double size, int doors, int windows, int lights, int fans) {
		super();
		this.name = name;
		this.size = size;
		this.doors = doors;
		this.windows = windows;
		this.lights = lights;
		this.fans = fans;
	}

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

	public List<RoomData> getRoomDataList() {
		return roomDataList;
	}

	public void setRoomDataList(List<RoomData> roomDataList) {
		this.roomDataList = roomDataList;
	}

}
