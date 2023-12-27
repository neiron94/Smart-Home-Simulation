package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;

public class ElectricOven extends Oven implements ElectricityConsumer {

    public ElectricOven(int id, Room startRoom) {
        super(DeviceType.ELECTRIC_OVEN, id, startRoom);
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
