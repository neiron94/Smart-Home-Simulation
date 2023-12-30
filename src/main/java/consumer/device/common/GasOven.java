package consumer.device.common;

import consumer.GasConsumer;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;
import utils.HelpFunctions;

public class GasOven extends Oven implements GasConsumer {

    public GasOven(int id, Room startRoom) {
        super(DeviceType.GAS_OVEN, id, startRoom);
    }

    @Override
    public double consumeGas() {
        return HelpFunctions.countGasConsumption(status, temperature / 2);      // TODO - change 2 for Constant (temperature for 1m^3)
    }

    @Override
    public void setStatus(DeviceStatus status) {
        if (status != DeviceStatus.STANDBY)
            this.status = status;
    }
}
