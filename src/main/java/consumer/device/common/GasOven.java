package consumer.device.common;

import consumer.GasConsumer;
import consumer.device.DeviceStatus;
import consumer.device.Manual;
import place.Room;

public class GasOven extends Oven implements GasConsumer {

    public GasOven(Room startRoom) {
        super(DeviceStatus.OFF, null, startRoom);  // TODO - manual should be taken from somewhere
    }

    @Override
    public void consumeGas() {
        // TODO - implement, depends on temperature
    }

    @Override
    public void leak() {
        // TODO - implement
    }

    @Override
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }
}
