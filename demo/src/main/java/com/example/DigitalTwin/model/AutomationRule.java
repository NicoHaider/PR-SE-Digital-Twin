package com.example.DigitalTwin.model;

import com.example.DigitalTwin.dto.AutomationRuleDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Data
@AllArgsConstructor
public class AutomationRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trigger;
    private String action;
    private String condition;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public AutomationRule(Long id, String trigger, String action, String condition) {
        this.id = id;
        this.trigger = trigger;
        this.action = action;
        this.condition = condition;
    }

    public AutomationRule() {

    }

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

    public AutomationRuleDto getDto() {
        AutomationRuleDto dto = new AutomationRuleDto();

        dto.setId(id);
        dto.setTrigger(trigger);
        dto.setAction(action);
        dto.setCondition(condition);
        dto.setRoomId(room.getId());
        return dto;
    }
}