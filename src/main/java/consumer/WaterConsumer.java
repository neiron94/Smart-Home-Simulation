package consumer;

import consumer.device.Device;
import event.FloodEvent;

/**
 * Devices, which consumes water, implements this interface.
 */
public interface WaterConsumer extends Consumer {

    /**
     * Counts amount of water consumed during this simulation tick by this consumer.
     * @return consumed water (in liters).
     */
    double consumeWater();
}
