package com.example.DigitalTwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.DigitalTwin.model.DeviceData;

public interface DeviceDataRepository extends JpaRepository<DeviceData, Long> {

    List<DeviceData> findByRoomId(Long roomId);
}
