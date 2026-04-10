package I;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import I._new.*;

public class WashingMachineTest {

    private WashingMachine washingMachine;
    private AirConditioner airConditioner;

    @Before
    public void setUp() {
        washingMachine = new WashingMachine();
        airConditioner = new AirConditioner();
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
    public void testWashingMachineDoesNotImplementHeatable() {
        assertNotNull("The washingMachine should not be null", washingMachine);
        assertFalse("WashingMachine should NOT implement Heatable", washingMachine instanceof Heatable);
    }

    @Test
    public void testWashingMachineDoesNotImplementCoolable() {
        assertNotNull("The washingMachine should not be null", washingMachine);
        assertFalse("WashingMachine should NOT implement Coolable", washingMachine instanceof Coolable);
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
        assertNotNull("The washingMachine should not be null", washingMachine);

        assertTrue("WashingMachine should implement MachineActions", washingMachine instanceof MachineActions);
        assertTrue("WashingMachine should implement Washable", washingMachine instanceof Washable);

        assertFalse("WashingMachine should NOT implement Heatable", washingMachine instanceof Heatable);
        assertFalse("WashingMachine should NOT implement Coolable", washingMachine instanceof Coolable);
    }

    @Test
    public void testCompareWithAirConditioner() {
        assertNotNull("The washingMachine should not be null", washingMachine);
        assertNotNull("The airConditioner should not be null", airConditioner);

        assertTrue("Both should implement MachineActions",
                washingMachine instanceof MachineActions && airConditioner instanceof MachineActions);

        assertTrue("WashingMachine should implement Washable", washingMachine instanceof Washable);
        assertTrue("AirConditioner should implement Heatable", airConditioner instanceof Heatable);
        assertTrue("AirConditioner should implement Coolable", airConditioner instanceof Coolable);

        assertFalse("WashingMachine should NOT implement Heatable", washingMachine instanceof Heatable);
        assertFalse("AirConditioner should NOT implement Washable", airConditioner instanceof Washable);
    }

    @Test
    public void testWashingMachineLifecycle() {
        assertNotNull("The washingMachine should not be null", washingMachine);

        washingMachine.turnOn();
        washingMachine.wash();
        washingMachine.turnOff();
    }

    @Test
    public void testWashingMachineAsMachineActions() {
        MachineActions machine = washingMachine;

        assertNotNull("Machine should not be null", machine);
        machine.turnOn();
        machine.turnOff();
    }

    @Test
    public void testWashingMachineAsWashable() {
        Washable washer = washingMachine;

        assertNotNull("Washer should not be null", washer);
        washer.wash();
    }

    @Test
    public void testMultipleMachinesWithCommonInterface() {
        MachineActions[] machines = new MachineActions[2];
        machines[0] = washingMachine;
        machines[1] = airConditioner;

        for (MachineActions machine : machines) {
            assertNotNull("Machine should not be null", machine);
            machine.turnOn();
            machine.turnOff();
        }
    }
}
