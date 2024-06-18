package com.example.DigitalTwin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.example.DigitalTwin.model.AutomationRule;
import com.example.DigitalTwin.service.AutomationRuleService;

@RestController
@RequestMapping("/api/rules")
public class AutomationRuleController{
    @Autowired
    private AutomationRuleService automationRuleService;

    @GetMapping
    public List<AutomationRule> getAllRules() {
        return automationRuleService.getAllAutomationRules();
    } // getAllRules instead getAllAutomationRules

    @PostMapping
    public ResponseEntity<AutomationRule> createAutomationRule(@RequestBody AutomationRule automationRule) {
        AutomationRule createdRule = automationRuleService.saveAutomationRule(automationRule);
        return ResponseEntity.status(201).body(createdRule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAutomationRule(@PathVariable Long id) {
        automationRuleService.deleteAutomationRule(id);
        return ResponseEntity.ok().build();
    }
}