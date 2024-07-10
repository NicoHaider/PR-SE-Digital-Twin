package com.example.DigitalTwin.repository;

import com.example.DigitalTwin.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    void deleteAllByRoomId(Long id);

    List<Device> findAllByRoomId(Long id);
}
