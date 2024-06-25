package com.example.DigitalTwin.engine;

import com.example.DigitalTwin.model.AutomationRule;
import com.example.DigitalTwin.model.Room;
import com.example.DigitalTwin.repository.AutomationRuleRepository;
import com.example.DigitalTwin.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AutomationRuleEngineTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private AutomationRuleRepository automationRuleRepository;

    @InjectMocks
    private AutomationRuleEngine automationRuleEngine;

    private Room room1;
    private Room room2;
    private AutomationRule rule1;
    private AutomationRule rule2;

    @BeforeEach
    public void setUp() {
        room1 = new Room();
        room1.setId(1L);
        room1.setTemperature(22.0);
        room1.setCo2(400.0);
        room1.setPeopleCount(0);
        room1.setWindows(1);
        room1.setLights(1);
        room1.setFans(1);
        room1.setDoors(1);

        room2 = new Room();
        room2.setId(2L);
        room2.setTemperature(25.0);
        room2.setCo2(500.0);
        room2.setPeopleCount(2);
        room2.setWindows(2);
        room2.setLights(2);
        room2.setFans(2);
        room2.setDoors(2);

        rule1 = new AutomationRule();
        rule1.setId(1L);
        rule1.setCondition("temperature > 23");
        rule1.setAction("turn_on_fan");

        rule2 = new AutomationRule();
        rule2.setId(2L);
        rule2.setCondition("peopleCount = 0");
        rule2.setAction("turn_off_light");
    }

    @Test
    public void testCheckRules() {
        when(roomRepository.findAll()).thenReturn(Arrays.asList(room1, room2));
        when(automationRuleRepository.findAll()).thenReturn(Arrays.asList(rule1, rule2));

        automationRuleEngine.checkAndApplyRules();

        // Verify actions taken on room1
        assertEquals(0, room1.getWindows());
        assertEquals(0, room1.getLights());
        assertEquals(1, room1.getFans());
        assertEquals(1, room1.getDoors());

        // Verify actions taken on room2
        assertEquals(2, room2.getWindows());
        assertEquals(2, room2.getLights());
        assertEquals(3, room2.getFans()); // Fan turned on because temperature > 23
        assertEquals(2, room2.getDoors());

        // Verify save method calls
        verify(roomRepository, times(3)).save(room1);
        verify(roomRepository, times(1)).save(room2);
    }

    @Test
    public void testEvaluateCondition() throws Exception {
        Method evaluateCondition = AutomationRuleEngine.class.getDeclaredMethod("evaluateCondition", String.class, Room.class);
        evaluateCondition.setAccessible(true);

        // Test temperature condition
        assertTrue((boolean) evaluateCondition.invoke(automationRuleEngine, "temperature > 21", room1));
        assertFalse((boolean) evaluateCondition.invoke(automationRuleEngine, "temperature < 21", room1));

        // Test CO2 condition
        assertTrue((boolean) evaluateCondition.invoke(automationRuleEngine, "co2 = 400", room1));
        assertFalse((boolean) evaluateCondition.invoke(automationRuleEngine, "co2 > 500", room1));

        // Test people count condition
        assertTrue((boolean) evaluateCondition.invoke(automationRuleEngine, "peopleCount = 0", room1));
        assertFalse((boolean) evaluateCondition.invoke(automationRuleEngine, "peopleCount > 0", room1));
    }

    @Test
    public void testExecuteAction() throws Exception {
        Method executeAction = AutomationRuleEngine.class.getDeclaredMethod("executeAction", String.class, Room.class);
        executeAction.setAccessible(true);

        // Test turning on fan
        executeAction.invoke(automationRuleEngine, "turn_on_fan", room1);
        assertEquals(2, room1.getFans());

        // Test turning off light
        executeAction.invoke(automationRuleEngine, "turn_off_light", room1);
        assertEquals(0, room1.getLights());

        // Test locking door
        executeAction.invoke(automationRuleEngine, "lock_door", room1);
        assertEquals(2, room1.getDoors());
    }

    @Test
    public void testCloseAllWindows() throws Exception {
        Method closeAllWindows = AutomationRuleEngine.class.getDeclaredMethod("closeAllWindows", Room.class);
        closeAllWindows.setAccessible(true);

        closeAllWindows.invoke(automationRuleEngine, room1);
        assertEquals(0, room1.getWindows());
        verify(roomRepository, times(1)).save(room1);
    }

    @Test
    public void testTurnOffAllLights() throws Exception {
        Method turnOffAllLights = AutomationRuleEngine.class.getDeclaredMethod("turnOffAllLights", Room.class);
        turnOffAllLights.setAccessible(true);

        turnOffAllLights.invoke(automationRuleEngine, room1);
        assertEquals(0, room1.getLights());
        verify(roomRepository, times(1)).save(room1);
    }
}
