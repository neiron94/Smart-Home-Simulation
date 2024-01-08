package consumer;

import consumer.device.Device;
import event.FireEvent;

public interface ElectricityConsumer extends Consumer {
    double consumeElectricity();
}
