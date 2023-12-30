package consumer;

import consumer.device.Device;
import event.FireEvent;

public interface ElectricityConsumer extends Consumer {
    int ELECTRICITY_COST = 0;   // cost for unit of electricity TODO - move to Constants and give value

    int consumeElectricity();

    default void fire() {
        if (this instanceof Device device) {    // TODO - can we remove instanceof?
            new FireEvent(device, device.getRoom()).throwEvent();
        }
        // TODO - add else?
    }
}
