package consumer;

import consumer.device.Device;
import event.LeakEvent;

public interface GasConsumer extends Consumer {
    double consumeGas();
}
