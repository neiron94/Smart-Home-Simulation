package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.DeviceStatus;
import place.Room;

public class ElectricOven extends Oven implements ElectricityConsumer {

    public ElectricOven(Room startRoom) {
        super(DeviceStatus.STANDBY, null, startRoom);  // TODO - manual should be taken from somewhere
    }

    @Override
    public void consumeElectricity() {
        // TODO - implement, depends on temperature
    }

    @Override
    public void fire() {
        // TODO - implement
    }
}
