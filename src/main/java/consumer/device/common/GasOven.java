package consumer.device.common;

import consumer.GasConsumer;
import consumer.device.DeviceStatus;
import consumer.device.DeviceType;
import place.Room;
import utils.Constants.Consumption.Gas;
import utils.HelpFunctions;

public class GasOven extends Oven implements GasConsumer {

    public GasOven(int id, Room startRoom) {
        super(DeviceType.GAS_OVEN, id, startRoom);
    }

    @Override
    public double consumeGas() {
        return HelpFunctions.countGasConsumption(status, Gas.OVEN * temperature / MAX_TEMPERATURE);
    }

    @Override
    public void setStandby() {}
}
