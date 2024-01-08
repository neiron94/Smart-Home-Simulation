package consumer;

import consumer.device.Device;
import event.FloodEvent;

public interface WaterConsumer extends Consumer {
    double consumeWater();
}
