package consumer;

import consumer.device.Device;
import event.LeakEvent;

public interface GasConsumer extends Consumer {
    int GAS_COST = 0;   // cost for unit of gas TODO - move to Constants and give value

    int consumeGas();

    default void leak() {
        if (this instanceof Device device) {    // TODO - can we remove instanceof?
            new LeakEvent(device, device.getRoom()).throwEvent();
        }
        // TODO - add else?
    }
}
