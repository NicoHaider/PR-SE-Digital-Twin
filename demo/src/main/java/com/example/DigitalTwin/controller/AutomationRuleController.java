package com.example.DigitalTwin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.example.DigitalTwin.dto.DeviceDto;
import com.example.DigitalTwin.model.Device;
import com.example.DigitalTwin.service.RoomService;
import com.example.DigitalTwin.model.AutomationRule;
import com.example.DigitalTwin.service.AutomationRuleService;
import com.example.DigitalTwin.dto.AutomationRuleDto;

import com.example.DigitalTwin.repository.AutomationRuleRepository;

@RestController
@RequestMapping("/api/rules")
public class AutomationRuleController{
    @Autowired
    private AutomationRuleService automationRuleService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private AutomationRuleRepository automationRuleRepository;

    private static final Logger logger = LogManager.getLogger(DeviceController.class);

    @GetMapping
    public List<AutomationRule> getAllRules() {
        return automationRuleService.getAllAutomationRules();
    }

    @PostMapping
    public ResponseEntity<?> createAutomationRule(@RequestBody AutomationRuleDto automationRule) {
        logger.info("Entering createAutomationRule method");
        System.out.println("in create automationRule controller ");
        try{
            logger.info("Creating automationRule: {}", automationRule);
            return ResponseEntity.status(HttpStatus.CREATED).body(automationRuleService.saveAutomationRule(automationRule));
        } catch (EntityNotFoundException e){
            logger.error("Entity not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e){
            logger.error("An error occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAutomationRule(@PathVariable Long id) {
        automationRuleService.deleteAutomationRule(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<?> updateAutomationRule(@RequestBody AutomationRuleDto automationRuleDto) {
        System.out.println("in create device controller ");
        logger.info("Entering updateDevice method");
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(automationRuleService.updateAutomationRule(automationRuleDto));
        } catch (EntityNotFoundException e){
            logger.error("Entity not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e){
            logger.error("An error occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went Wrong.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAutomationRuleById(@PathVariable Long id) {
        logger.info("Entering getRuleById method");
        try{
            return ResponseEntity.status(HttpStatus.OK).body(automationRuleService.getById(id));
        } catch (EntityNotFoundException e){
            logger.error("Entity not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}