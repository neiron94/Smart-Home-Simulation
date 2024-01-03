package consumer;

import consumer.device.Device;
import event.LeakEvent;

public interface GasConsumer extends Consumer {
    double consumeGas();

    default void leak() {
        if (this instanceof Device device) {    // TODO - can we remove instanceof?
            new LeakEvent(device, device.getRoom()).throwEvent();
        }
        // TODO - add else?
    }
}
