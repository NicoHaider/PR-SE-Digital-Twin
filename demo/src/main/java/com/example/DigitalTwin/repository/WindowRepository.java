package com.example.DigitalTwin.repository;

import com.example.DigitalTwin.model.components.Window;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WindowRepository extends JpaRepository<Window, Integer> {

	Optional<Window> findById(Long id);

	void deleteById(Long id);
	
    // Hier könnten spezifische Methoden definiert werden, falls nötig.
}
