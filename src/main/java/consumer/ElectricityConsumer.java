package consumer;

import consumer.device.Device;
import event.FireEvent;

public interface ElectricityConsumer extends Consumer {
    double consumeElectricity();

    default void fire() {
        if (this instanceof Device device) {    // TODO - can we remove instanceof?
            new FireEvent(device, device.getRoom()).throwEvent();
        }
        // TODO - add else?
    }
}
