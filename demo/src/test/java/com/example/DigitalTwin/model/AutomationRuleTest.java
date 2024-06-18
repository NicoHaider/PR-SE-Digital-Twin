package com.example.DigitalTwin.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AutomationRuleTest {

    private AutomationRule automationRule1;
    private AutomationRule automationRule2;

    @BeforeEach
    public void setUp() {
        automationRule1 = new AutomationRule(1L, "trigger1", "action1", "condition1");
        automationRule2 = new AutomationRule();
        automationRule2.setId(2L);
        automationRule2.setTrigger("trigger2");
        automationRule2.setAction("action2");
        automationRule2.setCondition("condition2");
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(1L, automationRule1.getId());
        assertEquals("trigger1", automationRule1.getTrigger());
        assertEquals("action1", automationRule1.getAction());
        assertEquals("condition1", automationRule1.getCondition());
    }

    @Test
    public void testSetters() {
        automationRule1.setId(3L);
        automationRule1.setTrigger("trigger3");
        automationRule1.setAction("action3");
        automationRule1.setCondition("condition3");

        assertEquals(3L, automationRule1.getId());
        assertEquals("trigger3", automationRule1.getTrigger());
        assertEquals("action3", automationRule1.getAction());
        assertEquals("condition3", automationRule1.getCondition());
    }

    @Test
    public void testEqualsAndHashCode() {
        AutomationRule anotherRule = new AutomationRule(1L, "trigger1", "action1", "condition1");
        assertEquals(automationRule1, anotherRule);
        assertEquals(automationRule1.hashCode(), anotherRule.hashCode());

        anotherRule.setId(2L);
        assertNotEquals(automationRule1, anotherRule);
        assertNotEquals(automationRule1.hashCode(), anotherRule.hashCode());
    }

    @Test
    public void testToString() {
        String expectedString = "AutomationRule(id=1, trigger=trigger1, action=action1, condition=condition1)";
        assertEquals(expectedString, automationRule1.toString());
    }

    @Test
    public void testNoArgsConstructor() {
        assertNotNull(automationRule2);
        assertEquals(2L, automationRule2.getId());
        assertEquals("trigger2", automationRule2.getTrigger());
        assertEquals("action2", automationRule2.getAction());
        assertEquals("condition2", automationRule2.getCondition());
    }
}
