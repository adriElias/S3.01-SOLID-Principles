package I;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import I._new.*;

public class AirConditionerTest {
    
    private AirConditioner airConditioner;
    private WashingMachine washingMachine;
    
    @Before
    public void setUp() {
        airConditioner = new AirConditioner();
        washingMachine = new WashingMachine();
    }
    
    @Test
    public void testAirConditionerIsNotNull() {
        assertNotNull("The airConditioner should not be null", airConditioner);
    }
    
    @Test
    public void testAirConditionerImplementsMachineActions() {
        assertNotNull("The airConditioner should not be null", airConditioner);
        assertTrue("AirConditioner should implement MachineActions", airConditioner instanceof MachineActions);
    }
    
    @Test
    public void testAirConditionerImplementsHeatable() {
        assertNotNull("The airConditioner should not be null", airConditioner);
        assertTrue("AirConditioner should implement Heatable", airConditioner instanceof Heatable);
    }
    
    @Test
    public void testAirConditionerImplementsCoolable() {
        assertNotNull("The airConditioner should not be null", airConditioner);
        assertTrue("AirConditioner should implement Coolable", airConditioner instanceof Coolable);
    }
    
    @Test
    public void testAirConditionerTurnOn() {
        assertNotNull("The airConditioner should not be null", airConditioner);
        airConditioner.turnOn();
    }
    
    @Test
    public void testAirConditionerTurnOff() {
        assertNotNull("The airConditioner should not be null", airConditioner);
        airConditioner.turnOff();
    }
    
    @Test
    public void testAirConditionerHeat() {
        assertNotNull("The airConditioner should not be null", airConditioner);
        assertTrue("AirConditioner should implement Heatable", airConditioner instanceof Heatable);
        airConditioner.heat();
    }
    
    @Test
    public void testAirConditionerCool() {
        assertNotNull("The airConditioner should not be null", airConditioner);
        assertTrue("AirConditioner should implement Coolable", airConditioner instanceof Coolable);
        airConditioner.cool();
    }
    
    @Test
    public void testWashingMachineIsNotNull() {
        assertNotNull("The washingMachine should not be null", washingMachine);
    }
    
    @Test
    public void testWashingMachineImplementsMachineActions() {
        assertNotNull("The washingMachine should not be null", washingMachine);
        assertTrue("WashingMachine should implement MachineActions", washingMachine instanceof MachineActions);
    }
    
    @Test
    public void testWashingMachineImplementsWashable() {
        assertNotNull("The washingMachine should not be null", washingMachine);
        assertTrue("WashingMachine should implement Washable", washingMachine instanceof Washable);
    }
    
    @Test
    public void testWashingMachineTurnOn() {
        assertNotNull("The washingMachine should not be null", washingMachine);
        washingMachine.turnOn();
    }
    
    @Test
    public void testWashingMachineTurnOff() {
        assertNotNull("The washingMachine should not be null", washingMachine);
        washingMachine.turnOff();
    }
    
    @Test
    public void testWashingMachineWash() {
        assertNotNull("The washingMachine should not be null", washingMachine);
        assertTrue("WashingMachine should implement Washable", washingMachine instanceof Washable);
        washingMachine.wash();
    }
    
    @Test
    public void testInterfaceSegregationPrinciple() {
        assertFalse("AirConditioner should NOT implement Washable", airConditioner instanceof Washable);
        
        assertFalse("WashingMachine should NOT implement Heatable", washingMachine instanceof Heatable);
        assertFalse("WashingMachine should NOT implement Coolable", washingMachine instanceof Coolable);
    }
    
    @Test
    public void testMachineActionsPolymorphism() {
        MachineActions[] machines = new MachineActions[2];
        machines[0] = airConditioner;
        machines[1] = washingMachine;
        
        for (MachineActions machine : machines) {
            assertNotNull("Machine should not be null", machine);
            machine.turnOn();
            machine.turnOff();
        }
    }
    
    @Test
    public void testSpecificInterfaceUsage() {
        Heatable heatable = airConditioner;
        Coolable coolable = airConditioner;
        Washable washable = washingMachine;
        
        assertNotNull("Heatable should not be null", heatable);
        assertNotNull("Coolable should not be null", coolable);
        assertNotNull("Washable should not be null", washable);
        
        heatable.heat();
        coolable.cool();
        washable.wash();
    }
}
