package com.example.DigitalTwin.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AutomationRuleServiceTest {
/*
    @Mock
    private AutomationRuleRepository automationRuleRepository;

    @InjectMocks
    private AutomationRuleService automationRuleService;

    private AutomationRule automationRule;

    @BeforeEach
    public void setUp() {
        Room room = new Room();
        room.setId(1L);
        automationRule = new AutomationRule(1L, "trigger1", "action1", "condition1");
    }

    @Test
    public void testSaveAutomationRule() {
        when(automationRuleRepository.save(any(AutomationRule.class))).thenReturn(automationRule);

        AutomationRuleDto savedRule = automationRuleService.saveAutomationRule(automationRule.getDto());

        assertNotNull(savedRule);
        assertEquals(1L, savedRule.getId());
        assertEquals("trigger1", savedRule.getTrigger());
        assertEquals("action1", savedRule.getAction());
        assertEquals("condition1", savedRule.getCondition());

        verify(automationRuleRepository, times(1)).save(automationRule);
    }

    @Test
    public void testGetAllAutomationRules() {
        when(automationRuleRepository.findAll()).thenReturn(Arrays.asList(automationRule));

        List<AutomationRule> rules = automationRuleService.getAllAutomationRules();

        assertNotNull(rules);
        assertEquals(1, rules.size());
        assertEquals(automationRule, rules.get(0));

        verify(automationRuleRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteAutomationRule() {
        when(automationRuleRepository.findByid(anyLong())).thenReturn(Optional.of(automationRule));

        automationRuleService.deleteAutomationRule(1L);

        verify(automationRuleRepository, times(1)).findByid(1L);
        verify(automationRuleRepository, times(1)).delete(automationRule);
    }

    @Test
    public void testDeleteAutomationRule_NotFound() {
        when(automationRuleRepository.findByid(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> {
            automationRuleService.deleteAutomationRule(1L);
        });

        String expectedMessage = "Rule not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        verify(automationRuleRepository, times(1)).findByid(1L);
        verify(automationRuleRepository, never()).delete(any(AutomationRule.class));
    }*/
}
