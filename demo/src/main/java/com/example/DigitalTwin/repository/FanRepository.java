package com.example.DigitalTwin.repository;

import com.example.DigitalTwin.model.components.Fan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FanRepository extends JpaRepository<Fan, Long> {
    // Standard-CRUD-Operationen sind hier verfügbar
}
