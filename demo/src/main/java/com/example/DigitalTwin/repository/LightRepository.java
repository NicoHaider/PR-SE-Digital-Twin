package com.example.DigitalTwin.repository;


import com.example.DigitalTwin.model.components.Light;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LightRepository extends JpaRepository<Light, Long> {
    // Standard-CRUD-Operationen sind durch JpaRepository bereitgestellt
}