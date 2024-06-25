package com.example.DigitalTwin.controller;

import com.example.DigitalTwin.model.AutomationRule;
import com.example.DigitalTwin.service.AutomationRuleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AutomationRuleController.class)
public class AutomationRuleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AutomationRuleService automationRuleService;

    private AutomationRule automationRule1;
    private AutomationRule automationRule2;

    @BeforeEach
    public void setUp() {
        automationRule1 = new AutomationRule(1L, "trigger1", "action1", "condition1");
        automationRule2 = new AutomationRule(2L, "trigger2", "action2", "condition2");
    }

    @Test
    public void testGetAllRules() throws Exception {
        List<AutomationRule> rules = Arrays.asList(automationRule1, automationRule2);
        when(automationRuleService.getAllAutomationRules()).thenReturn(rules);

        mockMvc.perform(get("/api/rules"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].trigger", is("trigger1")))
                .andExpect(jsonPath("$[0].action", is("action1")))
                .andExpect(jsonPath("$[0].condition", is("condition1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].trigger", is("trigger2")))
                .andExpect(jsonPath("$[1].action", is("action2")))
                .andExpect(jsonPath("$[1].condition", is("condition2")));
    }

    @Test
    public void testCreateAutomationRule() throws Exception {
        when(automationRuleService.saveAutomationRule(ArgumentMatchers.any(AutomationRule.class)))
                .thenReturn(automationRule1);

        mockMvc.perform(post("/api/rules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"trigger\": \"trigger1\", \"action\": \"action1\", \"condition\": \"condition1\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.trigger", is("trigger1")))
                .andExpect(jsonPath("$.action", is("action1")))
                .andExpect(jsonPath("$.condition", is("condition1")));
    }

    @Test
    public void testDeleteAutomationRule() throws Exception {
        doNothing().when(automationRuleService).deleteAutomationRule(1L);

        mockMvc.perform(delete("/api/rules/1"))
                .andExpect(status().isOk());
    }
}
