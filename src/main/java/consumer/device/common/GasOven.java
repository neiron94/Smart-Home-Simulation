/**
 * Copyright (c) 2024 Artem Liamkin, Ales Shvaibovich
 *
 * Licensed under the MIT License. See the LICENSE file for details.
 */

package consumer.device.common;

import consumer.GasConsumer;
import consumer.device.DeviceType;
import place.Room;
import utils.Constants.Consumption.Gas;
import utils.HelpFunctions;

/**
 * Gas oven in which person can cook food.
 */
public class GasOven extends Oven implements GasConsumer {

    public GasOven(int id, Room startRoom) {
        super(DeviceType.GAS_OVEN, id, startRoom);
    }

    @Override
    public double consumeGas() {
        return HelpFunctions.countGasConsumption(status, Gas.OVEN * temperature / MAX_TEMPERATURE);
    }

    @Override
    public GasOven copy() {
        return new GasOven(id, room);
    }
}
