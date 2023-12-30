package consumer;

import consumer.device.Device;
import event.FloodEvent;

public interface WaterConsumer extends Consumer {
    int WATER_COST = 0;   // cost for unit of water TODO - move to Constants and give value

    double consumeWater();

    default void flood() {
        if (this instanceof Device device) {    // TODO - can we remove instanceof?
            new FloodEvent(device, device.getRoom()).throwEvent();
        }
        // TODO - add else?
    }
}
