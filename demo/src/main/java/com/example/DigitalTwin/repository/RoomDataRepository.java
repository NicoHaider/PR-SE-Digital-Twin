package com.example.DigitalTwin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DigitalTwin.model.RoomData;

import java.util.List;

public interface RoomDataRepository extends JpaRepository<RoomData, Long> {

    List<RoomData> findByRoomId(Long roomId);
    
    void deleteByRoomId(Long roomId);
}
