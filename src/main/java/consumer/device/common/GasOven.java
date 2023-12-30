package consumer.device.common;

import consumer.GasConsumer;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;

public class GasOven extends Oven implements GasConsumer {

    public GasOven(int id, Room startRoom) {
        super(DeviceType.GAS_OVEN, id, startRoom);
    }

    @Override
    public int consumeGas() {
        // TODO - implement, depends on temperature
        return 0;
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
