package com.example.DigitalTwin.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DeviceData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private LocalDateTime dateTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;
    
    private int windows_on;
    
    private int fans_on;

    private int lights_on;

    private int doors_on;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 
    public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
    
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
    
    public int getWindows_on() {
        return windows_on;
    }

    public void setWindows_on(int windows_on) {
        this.windows_on = windows_on;
    }

    public int getFans_on() {
        return fans_on;
    }

    public void setFans_on(int fans_on) {
        this.fans_on = fans_on;
    }

    public int getLights_on() {
        return lights_on;
    }

    public void setLights_on(int lights_on) {
        this.lights_on = lights_on;
    }

    public int getDoors_on() {
        return doors_on;
    }

    public void setDoors_on(int doors_on) {
        this.doors_on = doors_on;
    }
}
