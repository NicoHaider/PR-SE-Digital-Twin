package com.example.DigitalTwin.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AutomationRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trigger;
    private String action;
    private String condition;
}