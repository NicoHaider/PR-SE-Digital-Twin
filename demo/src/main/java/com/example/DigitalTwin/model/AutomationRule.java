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

    public AutomationRule(Long id, String trigger, String action, String condition) {
        this.id = id;
        this.trigger = trigger;
        this.action = action;
        this.condition = condition;
    }

    public AutomationRule() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}