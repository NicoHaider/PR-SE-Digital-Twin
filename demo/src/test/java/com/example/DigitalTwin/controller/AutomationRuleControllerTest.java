package com.example.DigitalTwin.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AutomationRuleController.class)
public class AutomationRuleControllerTest {
/*
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AutomationRuleService automationRuleService;

    private AutomationRule automationRule1;
    private AutomationRule automationRule2;

    @BeforeEach
    public void setUp() {
        Room room = new Room();
        room.setId(1L);
        automationRule1 = new AutomationRule(1L, "trigger1", "action1", "condition1", room);
        automationRule2 = new AutomationRule(2L, "trigger2", "action2", "condition2", room);
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
        when(automationRuleService.saveAutomationRule(ArgumentMatchers.any(AutomationRuleDto.class)))
                .thenReturn(automationRule1.getDto());

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
    }*/
}
