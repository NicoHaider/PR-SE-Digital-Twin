package com.example.DigitalTwin.service;


import com.example.DigitalTwin.model.AutomationRule;
import com.example.DigitalTwin.repository.AutomationRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutomationRuleService {
    @Autowired
    private AutomationRuleRepository automationRuleRepository;

    public AutomationRule saveAutomationRule(AutomationRule automationRule){
        return automationRuleRepository.save(automationRule);
    }

    public List<AutomationRule> getAllAutomationRules(){
        return automationRuleRepository.findAll();
    }

    public void deleteAutomationRule(Long id) {
        AutomationRule automationRule = automationRuleRepository.findByid(id)
                .orElseThrow(() -> new NotFoundException("Rule not found"));
        automationRuleRepository.delete(automationRule);
    }

}