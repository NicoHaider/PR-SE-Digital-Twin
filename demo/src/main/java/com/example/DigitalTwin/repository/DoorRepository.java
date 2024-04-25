package com.example.DigitalTwin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DigitalTwin.model.components.Door;

public interface DoorRepository extends JpaRepository<Door, Integer> {

	Optional<Door> findById(Long id);

	void deleteById(Long id);
	
    // Standard-CRUD-Operationen sind hier verfügbar
}