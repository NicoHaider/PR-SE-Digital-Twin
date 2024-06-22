package com.example.DigitalTwin.service;


import com.example.DigitalTwin.exception.NotFoundException;
import com.example.DigitalTwin.model.AutomationRule;
import com.example.DigitalTwin.repository.AutomationRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Service
public class AutomationRuleService {
    private static final Logger logger = LogManager.getLogger(AutomationRuleService.class);
    @Autowired
    private AutomationRuleRepository automationRuleRepository;

    public AutomationRule saveAutomationRule(AutomationRule automationRule){
        logger.info("saveAutomationRule called");
        return automationRuleRepository.save(automationRule);
    }

    public List<AutomationRule> getAllAutomationRules(){
        logger.info("getAllAutomationRules called");
        return automationRuleRepository.findAll();
    }

    public void deleteAutomationRule(Long id) {
        logger.info("Request to delete automation rule with id: "+ id);
        try {
            AutomationRule automationRule = automationRuleRepository.findByid(id)
                    .orElseThrow(() -> new NotFoundException("Rule not found"));
            automationRuleRepository.delete(automationRule);
            logger.info("Automation rule with id " + id + " deleted successfully.");
        }catch (NotFoundException e ) {
            logger.error("Automation rule with id: "+ id+" not found", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error deleting automation rule with id: "+id, e);
            throw new RuntimeException("Error deleting automation rule.");
        }
    }

}