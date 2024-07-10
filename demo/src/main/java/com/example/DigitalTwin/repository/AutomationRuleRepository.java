package com.example.DigitalTwin.repository;

import com.example.DigitalTwin.model.AutomationRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutomationRuleRepository extends  JpaRepository<AutomationRule, Long> {
    Optional<AutomationRule> findByid(Long id); 
}