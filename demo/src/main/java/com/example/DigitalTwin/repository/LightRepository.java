package com.example.DigitalTwin.repository;


import com.example.DigitalTwin.model.components.Light;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LightRepository extends JpaRepository<Light, Integer> {

	Optional<Light> findById(Long id);

	void deleteById(Long id);
	
    // Standard-CRUD-Operationen sind durch JpaRepository bereitgestellt
}