package com.example.DigitalTwin.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class RoomDto {
	@NotEmpty(message = "Name is required")
	private String name;

	@NotNull(message = "Size count is required")
	@Positive(message = "Size must be positive")
	private Double size;

	@NotEmpty(message = "Name is required")
	private String type;

	private Long id;

	private List<DeviceDto> deviceDtoList;

//    @NotNull(message = "Windows count is required")
//    @PositiveOrZero(message = "Windows must be positive or zero")
//    private Integer windows;
//
//    @NotNull(message = "Lights count is required")
//    @PositiveOrZero(message = "Lights must be positive or zero")
//    private Integer lights;

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

//	public int getDoors() {
//		return doors;
//	}
//
//	public void setDoors(int doors) {
//		this.doors = doors;
//	}

//	public int getWindows() {
//		return windows;
//	}
//
//	public void setWindows(int windows) {
//		this.windows = windows;
//	}
//
//	public int getLights() {
//		return lights;
//	}
//
//	public void setLights(int lights) {
//		this.lights = lights;
//	}


	public @NotEmpty(message = "Name is required") String getType() {
		return type;
	}

	public void setType(@NotEmpty(message = "Name is required") String type) {
		this.type = type;
	}

	public List<DeviceDto> getDeviceDtoList() {
		return deviceDtoList;
	}

	public void setDeviceDtoList(List<DeviceDto> deviceDtoList) {
		this.deviceDtoList = deviceDtoList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
