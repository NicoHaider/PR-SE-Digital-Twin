package com.example.DigitalTwin.model;

import com.example.DigitalTwin.dto.DeviceDto;
import com.example.DigitalTwin.enums.DeviceType;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Boolean status;
    private Date time;

    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public DeviceDto getDto(){
        DeviceDto deviceDto = new DeviceDto();

        deviceDto.setId(id);
        deviceDto.setDeviceType(deviceType);
        deviceDto.setName(name);
        deviceDto.setTime(time);
        deviceDto.setStatus(status);
        deviceDto.setRoomId(room.getId());
        if (room != null) {
            deviceDto.setRoomId(room.getId());
        } else {
            System.out.println("Room is null");
            deviceDto.setRoomId(null);
        }

        return deviceDto;
    }
}
