package consumer;

import consumer.device.Device;
import event.LeakEvent;

/**
 * Devices, which consumes gas, implements this interface.
 */
public interface GasConsumer extends Consumer {

    /**
     * Counts amount of gas consumed during this simulation tick by this consumer.
     * @return consumed gas (in cubic meters).
     */
    double consumeGas();
}
