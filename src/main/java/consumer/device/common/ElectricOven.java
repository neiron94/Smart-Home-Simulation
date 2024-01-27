/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.common;

import consumer.ElectricityConsumer;
import consumer.device.DeviceType;
import place.Room;
import utils.Constants.Consumption.Electricity;
import utils.HelpFunctions;

/**
 * Electric oven for cooking.
 */
public class ElectricOven extends Oven implements ElectricityConsumer {

    public ElectricOven(int id, Room startRoom) {
        super(DeviceType.ELECTRIC_OVEN, id, startRoom);
    }

    @Override
    public double consumeElectricity() {
        return HelpFunctions.countElectricityConsumption(status, Electricity.OVEN * temperature / MAX_TEMPERATURE);
    }

    @Override
    public ElectricOven copy() {
        return new ElectricOven(id, room);
    }
}
