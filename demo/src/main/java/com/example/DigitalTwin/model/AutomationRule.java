package com.example.DigitalTwin.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutomationRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trigger;
    private String action;
    private String condition;

    //wäre alles wegen lombok nicht notwendig
    /*public AutomationRule(Long id, String trigger, String action, String condition) {
        this.id = id;
        this.trigger = trigger;
        this.action = action;
        this.condition = condition;
    }

    public AutomationRule() {

    }*/

    public void setId(Long id) {
        this.id = id;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}