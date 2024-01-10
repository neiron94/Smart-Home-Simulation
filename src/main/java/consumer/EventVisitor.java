package consumer;

import consumer.device.Device;
import consumer.device.DeviceStatus;
import event.FireEvent;
import event.FloodEvent;
import event.LeakEvent;

import static utils.Constants.Probabilities.DEVICE_EVENT_PROBABILITY;
import static utils.Constants.Probabilities.EVENT_DURABILITY_DEPENDENCY;

/**
 * With some probability makes consumer cause disaster event.
 */
public class EventVisitor implements Visitor {

    /**
     * Makes consumer cause fire event.
     * @param consumer ElectricityConsumer to visit.
     */
    @Override
    public void visit(ElectricityConsumer consumer) {
        Device device = (Device) consumer;
        if (device.getStatus() != DeviceStatus.OFF && shouldThrowEvent(device)) {
            new FireEvent(device, device.getRoom()).throwEvent();
            device.decreaseDurability(device.getDurability());
        }
    }

    /**
     * Makes consumer cause leak event.
     * @param consumer GasConsumer to visit.
     */
    @Override
    public void visit(GasConsumer consumer) {
        Device device = (Device) consumer;
        if (shouldThrowEvent(device)) {
            new LeakEvent(device, device.getRoom()).throwEvent();
            device.decreaseDurability(device.getDurability());
        }
    }

    /**
     * Makes consumer cause flood event.
     * @param consumer WaterConsumer to visit.
     */
    @Override
    public void visit(WaterConsumer consumer) {
        Device device = (Device) consumer;
        if (shouldThrowEvent(device)) {
            new FloodEvent(device, device.getRoom()).throwEvent();
            device.decreaseDurability(device.getDurability());
        }
    }

    private boolean shouldThrowEvent(Device device) {
        return Math.random() <= DEVICE_EVENT_PROBABILITY * (EVENT_DURABILITY_DEPENDENCY * (1 - 1.0 * device.getDurability() / device.getMaxDurability()));
    }
}
