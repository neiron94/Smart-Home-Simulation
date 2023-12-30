package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.DeviceType;
import place.Room;
import utils.HelpFunctions;

public class ElectricOven extends Oven implements ElectricityConsumer {

    public ElectricOven(int id, Room startRoom) {
        super(DeviceType.ELECTRIC_OVEN, id, startRoom);
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, temperature / 2); // TODO - change 2 for Constant (temperature for 1kW)
    }
}
