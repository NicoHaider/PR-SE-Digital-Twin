package com.example.DigitalTwin.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class RoomDto {
	@NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Size count is required")
    @Positive(message = "Size must be positive")
    private Double size;

    @NotNull(message = "Doors count is required")
    @PositiveOrZero(message = "Doors must be positive or zero")
    private Integer doors;

    @NotNull(message = "Windows count is required")
    @PositiveOrZero(message = "Windows must be positive or zero")
    private Integer windows;

    @NotNull(message = "Lights count is required")
    @PositiveOrZero(message = "Lights must be positive or zero")
    private Integer lights;

    @NotNull(message = "Fans count is required")
    @PositiveOrZero(message = "Fans must be positive or zero")
    private Integer fans;

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
}
