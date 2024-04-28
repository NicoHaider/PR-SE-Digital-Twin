package com.example.DigitalTwin.repository;

import com.example.DigitalTwin.model.components.Window;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WindowRepository extends JpaRepository<Window, Long> {
    // Hier könnten spezifische Methoden definiert werden, falls nötig.
}
