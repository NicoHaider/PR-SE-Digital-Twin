package com.example.DigitalTwin.service;


import com.example.DigitalTwin.dto.AutomationRuleDto;
import com.example.DigitalTwin.exception.NotFoundException;
import com.example.DigitalTwin.model.AutomationRule;
import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.repository.AutomationRuleRepository;
import com.example.DigitalTwin.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Service
public class AutomationRuleService {
    private static final Logger logger = LogManager.getLogger(AutomationRuleService.class);
    @Autowired
    private AutomationRuleRepository automationRuleRepository;

    @Autowired
    private RoomRepository roomRepo;

    public void createDefaultRules(Room room) {
        AutomationRuleDto closeWindowsIfTempBelow17 = new AutomationRuleDto();
        closeWindowsIfTempBelow17.setRoomId(room.getId());
        closeWindowsIfTempBelow17.setCondition("temperature < 17");
        closeWindowsIfTempBelow17.setAction("close_window");
        closeWindowsIfTempBelow17.setTrigger("temperature_sensor");
        saveAutomationRule(closeWindowsIfTempBelow17);

        AutomationRuleDto lightOffIfNoPeople = new AutomationRuleDto();
        lightOffIfNoPeople.setRoomId(room.getId());
        lightOffIfNoPeople.setCondition("peopleCount = 0");
        lightOffIfNoPeople.setAction("turn_off_light");
        lightOffIfNoPeople.setTrigger("people_sensor");
        saveAutomationRule(lightOffIfNoPeople);

        AutomationRuleDto openWindowIfCo2high = new AutomationRuleDto();
        openWindowIfCo2high.setRoomId(room.getId());
        openWindowIfCo2high.setCondition("co2_level > 500");
        openWindowIfCo2high.setAction("open_window");
        openWindowIfCo2high.setTrigger("co2_sensor");
        saveAutomationRule(openWindowIfCo2high);
    }

    public AutomationRuleDto saveAutomationRule(AutomationRuleDto automationRule){
        logger.info("saveAutomationRule called");
        Optional<Room> optionalRoom = roomRepo.findById(automationRule.getRoomId());
        if(optionalRoom.isPresent()) {
            AutomationRule newRule = new AutomationRule();
            newRule.setAction(automationRule.getAction());
            newRule.setCondition(automationRule.getCondition());
            newRule.setTrigger(automationRule.getTrigger());
            newRule.setRoom(optionalRoom.get());

            AutomationRuleDto createdRule = automationRuleRepository.save(newRule).getDto();
            logger.info("Rule created successfully: " + createdRule);
            return createdRule;
        } else {
            logger.error("Room not found with id: "+automationRule.getRoomId());
            throw new EntityNotFoundException("Room not present");
        }
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

    public AutomationRuleDto updateAutomationRule(AutomationRuleDto automationRuleDto) {
        logger.info("Request to update device: " + automationRuleDto);
        Optional<AutomationRule> existing = automationRuleRepository.findById(automationRuleDto.getId());

        if(existing.isPresent()) {
            AutomationRule automationRule = existing.get();

            automationRule.setAction(automationRuleDto.getAction());
            automationRule.setTrigger(automationRuleDto.getTrigger());
            automationRule.setCondition(automationRuleDto.getCondition());
            AutomationRuleDto updatedRule = automationRuleRepository.save(automationRule).getDto();
            logger.info("Device updated successfully: " + updatedRule);
            return updatedRule;
        } else {
            logger.error("Device with id: "+automationRuleDto.getId() + " not found.");
            throw new EntityNotFoundException("Device not present");
        }
    }

    public AutomationRuleDto getById(Long id) {
        logger.info("get AutomationRules by id");
        Optional<AutomationRule> optionalRule = automationRuleRepository.findById(id);
        if(optionalRule.isPresent()){
            AutomationRuleDto ruleDto = optionalRule.get().getDto();
            logger.info("AutomationRule retrieved successfully: "+ ruleDto);
            return ruleDto;
        } else {
            logger.error("AutomationRule not found with id: " + id);
            throw new EntityNotFoundException("AutomationRule not present");
        }
    }


}