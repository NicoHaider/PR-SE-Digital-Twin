package com.example.DigitalTwin.repository;

import com.example.DigitalTwin.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
	
}

