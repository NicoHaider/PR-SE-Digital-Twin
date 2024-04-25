package com.example.DigitalTwin.repository;

import com.example.DigitalTwin.model.components.Fan;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FanRepository extends JpaRepository<Fan, Integer> {

	Optional<Fan> findById(Long id);

	void deleteById(Long id);
	
    // Standard-CRUD-Operationen sind hier verfügbar
}
