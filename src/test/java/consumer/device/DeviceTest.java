package consumer.device;

import consumer.device.common.Dishwasher;
import consumer.device.common.Fridge;
import consumer.device.sensored.AC;
import event.EventType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import place.Room;
import smarthome.Simulation;
import utils.exceptions.DeviceIsBrokenException;
import utils.exceptions.ResourceNotAvailableException;

import static org.junit.jupiter.api.Assertions.*;

class DeviceTest {

    private static Simulation simulation;
    private static Room room;

    @BeforeAll
    static void init() {
        simulation = Simulation.getInstance();
        room = simulation.getHome().getFloors().get(0).getRooms().get(0);
    }

    @Test
    void deviceConstructorAddsToSimulationAndSupplySystemsTest() {
        Device device = new Dishwasher(0, room);

        assertTrue(simulation.getDevices().contains(device));
        assertTrue(simulation.getHome().getElectricitySupplySystem().getConsumedMap().containsKey(device));
        assertTrue(simulation.getHome().getWaterSupplySystem().getConsumedMap().containsKey(device));
    }

    @Test
    void deviceConstructorSetsCorrectlyTest() {
        Device device = new AC(0, room);

        assertSame(device.status, DeviceType.AC.getStartStatus());
        assertFalse(device.isOccupied);
        assertTrue(device.isFunctional);
        assertEquals(device.durability, device.getMaxDurability());
    }

    @Test
    void routineConsumesTest() {
        Fridge device = new Fridge(0, room);
        device.status = DeviceStatus.ON;
        device.routine();

        assertTrue(simulation.getHome().getElectricitySupplySystem().getConsumedMap().get(device) > 0);
    }

    @Test
    void routineDecreasesDurabilityTest() {
        Fridge device = new Fridge(0, room);
        device.routine();

        assertTrue(device.durability < device.getMaxDurability());
    }

    @Test
    void deviceTurnsOffAndThrowEventAfterBreakTest() {
        Fridge device = new Fridge(0, room);
        device.decreaseDurability(device.durability);

        assertSame(device.status, DeviceStatus.OFF);
        assertFalse(room.getEvents().stream()
                .filter(event -> event.getEventType() == EventType.BREAK && event.getCreator() == device)
                .toList().isEmpty());
    }

    @Test
    void cantTurnOnDeviceIfBrokenTest() {
        Fridge device = new Fridge(0, room);
        device.decreaseDurability(device.durability);

        assertThrows(DeviceIsBrokenException.class, device::turnOn);
    }

    @Test
    void cantTurnOnDeviceNoResourceTest() {
        Fridge device = new Fridge(0, room);
        room.setActiveElectricity(false);

        assertThrows(ResourceNotAvailableException.class, device::turnOn);
    }
}