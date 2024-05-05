package com.example.DigitalTwin.repository;

import com.example.DigitalTwin.model.components.Door;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoorRepository extends JpaRepository<Door, Long> {
    // Standard-CRUD-Operationen sind hier verfügbar
}