package consumer;

import consumer.device.Device;
import consumer.device.DeviceStatus;
import event.FireEvent;
import event.FloodEvent;
import event.LeakEvent;

import static utils.Constants.DEVICE_EVENT_PROBABILITY;
import static utils.Constants.EVENT_DURABILITY_DEPENDENCY;

public class EventVisitor implements Visitor {
    @Override
    public void visit(ElectricityConsumer consumer) {
        Device device = (Device) consumer;
        if (device.getStatus() != DeviceStatus.OFF && shouldThrowEvent(device)) {
            new FireEvent(device, device.getRoom()).throwEvent();
            device.decreaseDurability(device.getDurability());
        }
    }

    @Override
    public void visit(GasConsumer consumer) {
        Device device = (Device) consumer;
        if (shouldThrowEvent(device)) {
            new LeakEvent(device, device.getRoom()).throwEvent();
            device.decreaseDurability(device.getDurability());
        }
    }

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